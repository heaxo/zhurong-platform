package com.zhurong.platform.security.jwt;

import com.zhurong.platform.security.config.JwtProperties;
import com.zhurong.platform.security.model.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class JwtProvider {
    private final SecretKey secretKey;
    private final JwtProperties properties;

    public JwtProvider(JwtProperties properties) {
        this.properties = properties;

        byte[] keyBytes = Base64.getDecoder().decode(properties.getSecret());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(TokenUser user, List<String> roles) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("tenant", user.getTenantId())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + properties.getAccessExpire()))
                .signWith(secretKey)
                .compact();
    }

    public TokenUser parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Long userId = claims.get("id", Long.class);
            String username = claims.get("username", String.class);
            Long tenantId = claims.get("tenant", Long.class);
            List<String> roles = claims.get("roles", List.class);

            return TokenUser.builder()
                    .id(userId)
                    .username(username)
                    .tenantId(tenantId)
                    .roles(roles)
                    .build();

        } catch (ExpiredJwtException e) {
            throw e;
        } catch (JwtException e) {
            throw e;
        } catch (Exception e) {
            throw new JwtException("Invalid token structure", e);
        }
    }
}
