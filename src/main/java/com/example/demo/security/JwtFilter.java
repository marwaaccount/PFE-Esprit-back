package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.UtilisateurService;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class); // Logger declaration
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private JwtService jwtService;

    public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String username = null;
        boolean isTokenExpired = true;

        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            logger.debug("Received token: {}", token);
            
            try {
                isTokenExpired = jwtService.isTokenExpired(token);
                username = jwtService.extractUsername(token);
                logger.debug("Token is expired: {}", isTokenExpired);
                logger.debug("Extracted username: {}", username);
            } catch (Exception e) {
                logger.error("Error processing token: {}", e.getMessage());
            }
        } else {
            logger.warn("Authorization header is missing or invalid.");
        }

        if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = utilisateurService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.info("User  {} authenticated successfully.", username);
        } else {
            logger.warn("Authentication failed for username: {}. Token expired: {}", username, isTokenExpired);
        }

        filterChain.doFilter(request, response);
    }
}
