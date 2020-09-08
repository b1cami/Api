package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.json.LoginResponse;
import dgsw.b1cami.cocode.json.Response;

public interface UserService {

    Response sendEmail(User user);

    LoginResponse login(User user);

    Response signUp(User user);

    User findUser(String email);

}