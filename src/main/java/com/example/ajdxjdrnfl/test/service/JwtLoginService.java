package com.example.ajdxjdrnfl.test.service;

import com.example.ajdxjdrnfl.test.config.JwtUtil;
import com.example.ajdxjdrnfl.test.dto.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtLoginService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtUserDetailsService userDetailsService;

    public ResponseEntity<String> Login(LoginForm form) throws Exception {
        String token = createToken(form);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(token);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private String createToken(LoginForm form) throws Exception {
        authenticate(form.getID(), form.getPwd());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(form.getID());
        final String token = jwtUtil.generateToken(userDetails);

        return token;
    }

}
