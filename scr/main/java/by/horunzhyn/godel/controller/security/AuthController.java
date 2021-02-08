package by.horunzhyn.godel.controller.security;

import by.horunzhyn.godel.config.jwt.JwtProvider;
import by.horunzhyn.godel.entity.User;
import by.horunzhyn.godel.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setLogin(registrationRequest.getLogin());
        userService.saveUser(user);
        return "Its all be OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request){
        User user = userService.findByLoginAndPassword(request.getLogin(),request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }

}
