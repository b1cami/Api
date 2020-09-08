package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Token;
import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.json.LoginResponse;
import dgsw.b1cami.cocode.json.Response;
import dgsw.b1cami.cocode.json.UserResponse;

public interface UserService {

    Response sendEmail(User user);

    LoginResponse login(User user);

    Response signUp(User user);

    UserResponse getUser(String key);

}