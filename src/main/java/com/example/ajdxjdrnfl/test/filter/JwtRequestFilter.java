package com.example.ajdxjdrnfl.test.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ajdxjdrnfl.test.config.JwtUtil;
import com.example.ajdxjdrnfl.test.service.jwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

// check jwt token is valid
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private jwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = req.getHeader("Authorization");
        String token = null;
        String name = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            try {
                name = jwtUtil.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }

        // UsernamePasswordAuthenticationToken is a implementation of authentication
        // object.
        // authenticate token and store the authentication.
        if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(name);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

                // store the authenticated authentication in security context.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }
        // pass the request and the resposnse to next filter.
        filterChain.doFilter(req, res);

    }

}
