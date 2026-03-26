package edu.cibertec.login.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "Cibertec2026.42vfasfdsfsdrtertvnmfbenrnrtlnertretreergergnm,nlnvdfgndenjn";

    private static final SecretKey SECRET_KEY =Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    static void addAuthentication(HttpServletResponse res, String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+60000))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();

        res.addHeader("Authorization", "Bearer "+token);
    }

    static Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
            String user = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList())
                    : null;
        }
        return null;
    }

}
