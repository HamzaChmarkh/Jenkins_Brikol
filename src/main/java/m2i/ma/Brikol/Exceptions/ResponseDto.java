package m2i.ma.Brikol.Exceptions;

public class ResponseDto {

    private final String message;
    private final int status;

    public ResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
