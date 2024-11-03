package m2i.ma.Brikol.User.confirmation;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getToken(String token);



}
