package br.com.devbean.mode02.controllers;

import br.com.devbean.mode01.services.AuthenticationService;
import br.com.devbean.mode02.dtos.LoginAuthRequestDTO;
import br.com.devbean.mode02.dtos.LoginAuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth2")
public class LoginAuthController {

    private final AuthenticationService authorizationService;

    public LoginAuthController(AuthenticationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAuthResponseDTO> loginAuth(@RequestBody @Valid LoginAuthRequestDTO requestDTO) {

        return ResponseEntity.ok(
                new LoginAuthResponseDTO(
                        authorizationService.authenticate(
                                requestDTO.email(), requestDTO.password()
                        )
                )
        );
    }


    @PostMapping("/register")
    public ResponseEntity<String> loginAuth() {
        return ResponseEntity.ok("ADMIN ACCESS GRANTED");
    }


}
