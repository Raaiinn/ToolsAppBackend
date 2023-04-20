package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    private final String JWTSECRET = "mySecretKey";

    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        Claims claims = Jwts.parser().setSigningKey(JWTSECRET.getBytes()).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (!isTokenExpired);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(JWTSECRET.getBytes()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
