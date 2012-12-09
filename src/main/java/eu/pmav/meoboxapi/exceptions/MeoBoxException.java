package eu.pmav.meoboxapi.exceptions;

/**
 *
 * @author pmav
 */
public class MeoBoxException extends Exception
{

    public MeoBoxException(String message)
    {
        super(message);
    }

    
    
    public MeoBoxException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
