package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginResponse extends Response {

    String token;
    User user;

    public LoginResponse(int status, String message) {
        super(status, message);
    }

    public LoginResponse(int status, String message, String token, User user) {
        super(status, message);
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }
    public User getUser() { return this.user; }

}
