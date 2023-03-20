package com.anunnakisoftware.notes.security.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private String authHeader;
    private String jwt;
    private String userEmail;
    public JwtAuthenticationFilter(String authHeader, String jwt, String userEmail, JwtService jwtService) {
        this.authHeader = authHeader;
        this.jwt = jwt;
        this.userEmail = userEmail;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        authHeader  = request.getHeader("Authorization");


        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.getUsername(jwt);
    }
}
