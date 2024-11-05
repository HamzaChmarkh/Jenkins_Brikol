package m2i.ma.Brikol.Service;

public class ServcieException extends RuntimeException {
    public ServcieException(String message) {
        super(message);
    }


    public ServcieException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServcieException(Object object, String message) {

        this.checkNull(object, message);
    }

    private void checkNull(Object object, String message) {

        if (object == null) {
            throw new RuntimeException(message);
        } else if (message.isEmpty()) {
            throw new RuntimeException("empty message");
        }

    }
}