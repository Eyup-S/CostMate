package com.falcon.CostMate.Configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Configuration
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String roles = auth.getAuthorities().toString();

        System.out.println(url + " " + username + " " + roles) ;
        filterChain.doFilter(request, response);
    }

}