package client.LoginValidation;

public class LoginValidation {

    public static String username;
    public static String password;

    public static ValidationState validate(String username, String password) {
        if (!username.equals("tom")) return ValidationState.WRONG_USERNAME;
        else if (!password.equals("password")) return ValidationState.WRONG_PASSWORD;
        LoginValidation.username = username;
        LoginValidation.password = password;
        return ValidationState.SUCCESSFUL;
    }
}
