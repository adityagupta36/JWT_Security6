package aditya.SpringSecurity.JWT_Security6.Controller;

import aditya.SpringSecurity.JWT_Security6.Model.User;
import aditya.SpringSecurity.JWT_Security6.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.verify(user);
    }

}
