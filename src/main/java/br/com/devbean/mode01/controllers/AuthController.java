package br.com.devbean.mode01.controllers;

import br.com.devbean.mode01.services.AuthenticationService;
import br.com.devbean.mode01.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationService authenticationService, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        String token = authenticationService.authenticate(authRequest.username(), authRequest.password());
        String refreshToken = jwtUtil.generateRefreshToken(authRequest.username());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .expiresIn(jwtUtil.extractExpiration(token))
                .build();
    }

    @GetMapping("/admin")
    public String admin() {
        return "** ADMIN ** ACCESS CONTENT GRANTED!";
    }

    @GetMapping("/user")
    public String user() {
        return "- USER - ACCESS CONTENT GRANTED!";
    }
}