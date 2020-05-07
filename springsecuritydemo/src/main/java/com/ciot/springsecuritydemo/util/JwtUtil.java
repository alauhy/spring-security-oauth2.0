package com.ciot.springsecuritydemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Optional;

public class JwtUtil {

    private static final String SECRET = "ciot_api_jwt_secret";
    private static final String ISS = "ciot";
    private static final long EXPIRATION = 1800L;

    public static String createToken(String issuer, String subject, long expiration) {
        return createToken(issuer, subject, expiration, null);
    }

    public static String createToken(String issuer, String subject, long expiration, Claims claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(null)
                .setSubject(subject)
                .setIssuer(Optional.ofNullable(issuer).orElse(ISS))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (expiration > 0 ? expiration : EXPIRATION) * 1000))
                .compact();
    }

    public static String getProperties(String token) {
        return getTokenBody(token).getSubject();
    }

    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
