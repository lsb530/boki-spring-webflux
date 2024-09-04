package boki.learnjava.common;

public class TimezoneNotFoundException extends RuntimeException {
    public TimezoneNotFoundException(String message) {
        super(message);
    }
}