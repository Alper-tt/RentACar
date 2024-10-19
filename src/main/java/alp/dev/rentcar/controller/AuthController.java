package alp.dev.rentcar.controller;

import alp.dev.rentcar.model.request.LoginRequest;
import alp.dev.rentcar.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody LoginRequest loginRequest){
        authService.registerCustomer(loginRequest);
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.loginCustomer(loginRequest);
    }
}
