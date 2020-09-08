package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.Domain.UserOutput;

public class UserResponse extends Response {

    UserOutput user;

    public UserResponse(int status, String message) {
        super(status, message);
    }

    public UserResponse(int status, String message, User user) {
        super(status, message);
        this.user = new UserOutput(user);
    }

    public UserOutput getUser() {
        return user;
    }

}
