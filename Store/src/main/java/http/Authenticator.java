package http;

import com.sun.net.httpserver.BasicAuthenticator;

public class Authenticator extends BasicAuthenticator {

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Authenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(USER) && password.equals(PASSWORD);
    }
}

