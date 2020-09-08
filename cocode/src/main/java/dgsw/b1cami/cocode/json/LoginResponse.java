package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.Domain.UserOutput;

public class LoginResponse extends Response {

    String token;
    UserOutput user;

    public LoginResponse(int status, String message) {
        super(status, message);
    }

    public LoginResponse(int status, String message, String token, User user) {
        super(status, message);
        this.token = token;
        this.user = new UserOutput(user);
    }

    public String getToken() {
        return this.token;
    }
    public UserOutput getUser() { return this.user; }

}
