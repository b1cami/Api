package dgsw.b1cami.cocode.Controller;

import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.Service.UserService;
import dgsw.b1cami.cocode.json.LoginResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendEmail")
    public ResponseEntity<Response> sendEmail(@RequestBody User user) {
        System.out.println("user sendEmail - " + user.getEmail());
        return new ResponseEntity<>(userService.sendEmail(user), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Response> signUp(@RequestBody User user) {
        System.out.println("user signUp - " + user.getEmail());
        return new ResponseEntity<>(userService.signUp(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        System.out.println("user login - " + user.getEmail() + ", " + user.getPassword());
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

}