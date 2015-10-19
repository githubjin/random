package random.support;

/**
 * Created by DaoSui on 2015/10/17.
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
