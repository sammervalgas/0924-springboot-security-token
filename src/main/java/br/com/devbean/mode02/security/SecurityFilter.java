package br.com.devbean.mode02.security;

import br.com.devbean.mode02.repositories.UserAccessRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private  TokenService tokenService;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        String headerSource = request.getHeader("x-source");

        if(headerSource != null && headerSource.equals("M02")) {

            var token = this.recoveryToken(request);

            if (token != null) {
                var email = tokenService.extractSubject(token);
                UserDetails user = userAccessRepository.findByEmail(email);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;
        if(authHeader.contains("Bearer"))
            return authHeader.replace("Bearer ", "");

        throw new AuthenticationCredentialsNotFoundException("Authorization Type not allowed");
    }
}
