package m2i.ma.Brikol.Service;

public class ServcieException extends RuntimeException {
    public ServcieException(String message) {
        super(message);
    }

    public ServcieException(String message, Throwable cause) {
        super(message, cause);
    }
}

