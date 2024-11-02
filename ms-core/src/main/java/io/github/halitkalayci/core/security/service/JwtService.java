package io.github.halitkalayci.core.security.service;

import io.github.halitkalayci.core.security.dto.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService
{
  @Value("${jwt.key}")
  private String SECRET_KEY;
  @Value("${jwt.expiration}")
  private long EXPIRATION;

  public AccessToken generateToken(String userName) {
    Map<String,Object> claims = new HashMap<>();
    return createToken(claims,userName);
  }
  public AccessToken generateToken(String userName, Map<String,Object> claims) {
    return createToken(claims, userName);
  }
  public Boolean validateToken(String token) {
    Date expirationDate = extractExpiration(token);
    return expirationDate.after(new Date());
  }
  private Date extractExpiration(String token) {
    return getClaims(token).getExpiration();
  }
  public String extractUser(String token) {
    return getClaims(token).getSubject();
  }

  public Claims getClaims(String token)
  {
    return Jwts
            .parser()
            .verifyWith((SecretKey) getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }
  private AccessToken createToken(Map<String, Object> claims, String userName) {
    Date expiration = new Date(System.currentTimeMillis() + EXPIRATION);
    String jwt = Jwts.builder()
            .claims(claims)
            .subject(userName)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(expiration)
            .signWith(getSignKey())
            .compact();
    return new AccessToken(jwt, expiration);
  }
  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
