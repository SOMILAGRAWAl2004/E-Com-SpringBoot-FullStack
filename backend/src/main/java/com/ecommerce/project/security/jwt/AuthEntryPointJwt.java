package com.ecommerce.project.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    // 'AuthenticationEntryPoint' is used here to modify the response that the user gets when its request is unauthenticated

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);


    // This method is called automatically whenever authentication fails.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error : {}", authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Ensures client gets JSON instead of HTML or plain text.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //Returns 401 Unauthorized.

        // Building a JSON response body
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        //Writing JSON to response
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}