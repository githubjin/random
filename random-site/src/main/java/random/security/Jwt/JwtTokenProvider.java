package random.security.Jwt;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by DaoSui on 2015/10/18.
 */
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String secretKey;
    private final float tokenValidity;
    private final float notBeforeMinutesInThePast;
    private final String issuer;
    private final int allowedClockSkewInSeconds;
    private final String rsaKey = "JwtTokenProvider";
    private final int rsaKeyLength = 2048;
    private RsaJsonWebKey rsaJsonWebKey;

    public JwtTokenProvider(String secretKey, float tokenValidity, float notBeforeMinutesInThePast, String issuer, int allowedClockSkewInSeconds) throws JoseException {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
        this.notBeforeMinutesInThePast = notBeforeMinutesInThePast;
        this.issuer = issuer;
        this.allowedClockSkewInSeconds = allowedClockSkewInSeconds;
        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        this.rsaJsonWebKey = RsaJwkGenerator.generateJwk(this.rsaKeyLength);
        // Give the JWK a Key ID (kid), which is just the polite thing to do
        this.rsaJsonWebKey.setKeyId(this.rsaKey);
    }

    public String createToken(String userName, String email) throws JoseException {
        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(this.issuer);  // who creates the token and signs it
        claims.setAudience(userName); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(this.tokenValidity); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
	    claims.setNotBeforeMinutesInThePast(this.notBeforeMinutesInThePast); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject(userName); // the subject/principal is whom the token is about
        claims.setClaim("email", email); // additional claims/attributes about the subject can be added
//        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
//        claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(this.rsaJsonWebKey.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(this.rsaJsonWebKey.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt = jws.getCompactSerialization();
        logger.debug("-------------generate JWT-----------------{} 's jwt is {}", userName, jwt);
        return jwt;
    }

    public JwtClaims validateToken(String jwt, UserDetails userDetails) throws InvalidJwtException {
        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(this.allowedClockSkewInSeconds) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer(this.issuer) // whom the JWT needs to have been issued by
                .setExpectedAudience(userDetails.getUsername()) // to whom the JWT is intended for
                .setVerificationKey(this.rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        //  Validate the JWT and process it to the Claims
        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        logger.debug("JWT validation succeeded! {}",jwtClaims);
        return jwtClaims;
    }

    public boolean validateToken(JwtClaims claims, UserDetails userDetails) throws MalformedClaimException {
        String subject = claims.getSubject();
        boolean b = subject.equals(userDetails.getUsername());
        claims.getExpirationTime();
        return b;
    }
}
