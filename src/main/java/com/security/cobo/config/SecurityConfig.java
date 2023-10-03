package com.security.cobo.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityConfig /*extends OncePerRequestFilter*/ {

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        final String origin = "*";

        if (request != null && request.getHeader("origin") != null && !(request.getHeader("origin").equals("https://sturdy-space-spoon-4wq7pxr47r6f67w-8080.app.github.dev/"))) {
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Max-Age", Integer.toString((365 * 24 * 60 * 60)));
        }
    

        filterChain.doFilter(request, response);
    
    }*/
    
}
