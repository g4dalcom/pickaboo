package com.project.pickaboo.jwt;

import com.project.pickaboo.exception.CustomException;
import com.project.pickaboo.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final UserDetailsService userDetailsService;

    public TokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                         @Value("${security.jwt.token.access-token-expire-length}") final long accessTokenValidityInMilliseconds,
                         @Value("${security.jwt.token.refresh-token.expire.length}") final long refreshTokenValidityInMilliseconds,
                         final UserDetailsService userDetailsService) {

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.userDetailsService = userDetailsService;
    }

    public String generateAccessToken(final String userPK) {
        return createToken(userPK, accessTokenValidityInMilliseconds);
    }

    public String generateRefreshToken(final String userPK) {
        return createToken(userPK, refreshTokenValidityInMilliseconds);
    }

    public String createToken(final String userPK, final long validityInMilliseconds) {
        Claims claims = Jwts.claims().setSubject(userPK);
        Date now = new Date();
        Date expiredIn = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredIn)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 회원 정보 추출
    public String getUserPK(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(final String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return claims.getBody()
                        .getExpiration()
                        .after(new Date());
        }
        catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
