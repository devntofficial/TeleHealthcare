package com.bmc.doctorservice.services;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private final String JWT_SECRET ="some_secret_code";
    private final int JWT_EXPIRATION_TIME_IN_MS = 60000;

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        return  Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
    }
}
