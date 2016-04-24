package slosar.srt.spendingsapp.Exceptions;

/**
 * Created by Rafal on 2016-04-24.
 */
public class ExceptionEvent {
    public Exception cause;

    public ExceptionEvent(Exception e) {
        cause = e;
    }
}
