package dev.bernouy.cms.security;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "laclesecuriselamoinsecuriseaumondelaclesecuriselamoinsecuriseaumondelaclesecuriselamoinsecuriseaumonde";
    private static final long expirationTimeInMs = 1000*60*60*24*2;

    public static String generateJwt( String accountID ) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMs);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(accountID)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        return jwtBuilder.compact();
    }

    public static String getSubjectJwt(String jwt){
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt);
            Claims claims = jws.getBody();
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}