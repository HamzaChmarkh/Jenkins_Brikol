package m2i.ma.Brikol.User.resetpassword;

import lombok.AllArgsConstructor;

import java.security.SecureRandom;

@AllArgsConstructor
public class ResetPasswordUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateNumericToken() {
        int token = secureRandom.nextInt(900000) + 100000; // Generates a 6-digit number.
        return String.valueOf(token);
    }

}
