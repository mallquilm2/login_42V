package edu.cibertec.login.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication auhtentication = JwtUtil.getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(auhtentication);
        filterChain.doFilter(request, response);
    }

}
