package com.ecommerce.project.security.jwt;

import com.ecommerce.project.security.services.UserDetailServiceImpl;
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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("AuthTokenFilter called for URI : {}", request.getRequestURI());
        try {
            String jwt = parseJwt(request); // gets the JWT token from the authentication header
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromToken(jwt);  //getting the username from the token
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); //getting the user from the DB

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());  // this basically creates an ID card for the
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Adds extra info from the current request, like:  Remote IP address, Session ID etc
                SecurityContextHolder.getContext().setAuthentication(authentication); //“Spring, treat this user as logged-in for this request, with these roles.”
                logger.debug("Roles form JWT : {}", userDetails.getAuthorities());
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication : {}", e);
        }
        filterChain.doFilter(request, response); // "I’m done with my work, please continue processing this request through the rest of the filter chain until it reaches the controller (or another filter blocks it)."
    }
//    private String parseJwt(HttpServletRequest request) {
//        String jwt = jwtUtils.getJwtFromCookies(request);
//        logger.debug("AuthTokenFilter.java : {}", jwt);
//        return jwt;
//    }

    private String parseJwt(HttpServletRequest request) {
        String jwtFromCookie = jwtUtils.getJwtFromCookies(request);
        if (jwtFromCookie != null) {
            return jwtFromCookie;
        }

        String jwtFronHeader = jwtUtils.getJwtFromHeader(request);
        if (jwtFronHeader != null) {
            return jwtFronHeader;
        }

        return null;
    }
}