package br.com.devbean.mode02.security;

import br.com.devbean.mode01.utils.JwtUtil;
import br.com.devbean.mode02.models.UserModel;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtUtil jwtUtil;

    public TokenService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(UserModel userModel) {
        return jwtUtil.generateToken(userModel.getEmail());
    }


    public String extractSubject(String token) {
        return jwtUtil.extractUsername(token);
    }


}
