package com.ecommerce.project.security.jwt;

import com.ecommerce.project.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.net.ResponseCache;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class); //logger is a tool used to record messages, it also adds the logs onto the console (similar to system.out.println()) with all the additional details


    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${spring.ecom.app.jwtCookieName}")
    private String jwtCookie;

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    //--------------JWT COOKIE IMPLEMENTATION--------------
    // this method extracts JWT token from the browser cookie
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //generating a JWT Cookie
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60)
                .httpOnly(false)
                .build();

        return cookie;
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null)
                .path("/api")
                .build();

        return cookie;
    }

    //--------------JWT TOKEN GENERATION IMPLEMENTATION--------------
    // getting JWT from the header
/*    public String getJwtFromHeader(HttpServletRequest request) {
        //HttpServletRequest - represents the request object that client gives (it is the object that is given to the controllers when we hit 'Send' in the postman )
        // Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... --------example of what we get from 'authorization' header
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header : {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); //removing the bearer prefix
        }
        return null;
    }*/

    // generating token from the username
    public String generateTokenFromUsername(String username) { //Creates a new JWT for an authenticated user.
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime() + jwtExpirationMs)))
                .signWith(key())
                .compact();
    }

    // getting username from the JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();

        //Starts building a JWT parser.
        //Configures the parser with your secret key to verify signatures.
        //Finalizes the parser setup.
        //Parses and validates the given JWT string.
        //Extracts the claims (payload) from the token.
        //Returns the username stored in the token’s subfield.
    }

    // generate signing key
    public Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // validate JWT token
    public boolean validateJwtToken(String authToken) {
        try {
            System.out.println("Validate");
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);

            return true;
        } catch (MalformedJwtException exception) {
            logger.error("Invalid JWT token : {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            logger.error("JWT token is expired : {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            logger.error("JWT token is unsupported : {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            logger.error("JWT claims string is empty : {}", exception.getMessage());
        }
        return false;
    }
}