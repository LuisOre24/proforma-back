package com.soldev.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	
	
	  private static final String JWT_SECRET_KEY = "NdRgUkXp2s5v8y/B?E(H+MbPeShVmYq3";

	  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    return claimsResolver.apply(extractAllClaims(token));
	  }

	  private Claims extractAllClaims(String token) {
	    return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
	  }
	  
	  public boolean validateToken(String token, UserDetails userDetails) {
		    final String username = extractUsername(token);
		    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }

	  private Boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }
	  
	  public Date extractExpiration(String token) {
		    return extractClaim(token, Claims::getExpiration);
		  }

	  public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    // Agregando informacion adicional como "claim"
	    var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
	    claims.put("rol", rol);
	    return createToken(claims, userDetails.getUsername());
	  }
  
	  private String createToken(Map<String, Object> claims, String subject) {

		    return Jwts
		        .builder()
		        .setClaims(claims)
		        .setSubject(subject)
		        .setIssuedAt(new Date(System.currentTimeMillis()))
		        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
		        .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
		        .compact();
		  }
}
