package aditya.SpringSecurity.JWT_Security6.Services;

import aditya.SpringSecurity.JWT_Security6.Model.User;
import aditya.SpringSecurity.JWT_Security6.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    public User register(User user){
        String encrpytedPass = bCryptPasswordEncoder.encode(user.getPasswrod());
        user.setPasswrod(encrpytedPass);
        return userRepo.save(user);
    }

    public String verify(User user) throws NoSuchAlgorithmException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPasswrod()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }
        else return "Fail";
    }


}

