package random.configs;

import org.elasticsearch.common.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import random.Constants;
import random.rbac.Model.RolePermissionResources;
import random.rbac.service.RbacService;
import random.security.Http401UnauthorizedEntryPoint;
import random.security.xauth.XAuthTokenConfigurer;
import random.security.xauth.XauthTokenProvider;

import java.util.List;
import java.util.Optional;

/**
 * Created by DaoSui on 2015/10/18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Inject
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;
    @Inject
    private UserDetailsService userDetailsService;
    @Inject
    private XauthTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Inject
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * @Query("select m from Message m where m.to.id = ?#{ principal?.id }")
     * List<Message> findAll();
     * @return
     */
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/script/**/*.{js,html}")
                .antMatchers("/bower_components/**")
                .antMatchers("/i18n/**")
                .antMatchers("/assets/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/account/reset_password/init").permitAll()
            .antMatchers("/api/account/reset_password/finish").permitAll()
            .antMatchers("/api/logs/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/api/audits/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/api/**").authenticated()
            .antMatchers("/metrics/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/health/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/trace/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/dump/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/shutdown/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/beans/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/configprops/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/info/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/autoconfig/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/env/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/trace/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/mappings/**").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/configuration/security").permitAll()
            .antMatchers("/configuration/ui").permitAll()
            .antMatchers("/swagger-ui/index.html").hasAuthority(Constants.ROLE_ADMIN)
            .antMatchers("/protected/**").authenticated()
        .and()
            .apply(securityConfigurerAdapter());
    }

    private XAuthTokenConfigurer securityConfigurerAdapter() {
        return new XAuthTokenConfigurer(userDetailsService, tokenProvider);
    }
}
