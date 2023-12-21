package org.philipturbanovtz.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import org.philipturbanovtz.dtos.api.user.UserDto;
import org.philipturbanovtz.modules.auth.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Override
    public String generateToken(UserDto user) {
        return Jwts
            .builder()
            .setSubject(user.getId().toString())
            .setIssuedAt(Date.from(Instant.now()))
            .signWith(getKey())
            .compact();
    }

    @Override
    public Long extractUserId(String token) {
        try {
            Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey()).build()
                .parseClaimsJws(token)
                .getBody();

            if (claims == null) {
                return null;
            }

            return Long.valueOf(claims.getSubject());

        } catch (Throwable e) {
            return null;
        }
    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
