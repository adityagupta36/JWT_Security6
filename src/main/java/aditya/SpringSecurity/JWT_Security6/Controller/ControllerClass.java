package aditya.SpringSecurity.JWT_Security6.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @GetMapping("")
    public String greet(HttpServletRequest httpServletRequest){
        return "Welcome to the Page! " + httpServletRequest.getSession().getId();
    }

}
