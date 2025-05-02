package com.summitlib.util;

import java.awt.RenderingHints.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.summitlib.dao.UserDAO;
import com.summitlib.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Stateless
public class JwtUtil {
	
	private final Long EXPIRATION = 4*24*60*10000l;
	private String SECRET_KEY="secretsecretsecretsecretsecretsecretsecret";
	
	@Inject
	private UserDAO userDAO;
	
	public String generateJwtToken(User user) {
		
		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		
		 String jwtToken = Jwts.builder()
	                .setSubject(user.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
	                .signWith(key)
	                .compact();
		 
		 return jwtToken;
		
	}
	
	public boolean validateJwtToken(String token) {
		
		try {
			SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
			 Claims claims = Jwts.parserBuilder()
	                 .setSigningKey(key)
	                 .build()
	                 .parseClaimsJws(token)
	                 .getBody();
			
			if(userDAO.isUserExists(claims.getSubject())) {
				return true;
			}
			
			return false;
		}catch(Exception e) {
			System.out.println(e + "jwt error");
			return false;
		}
	
	}
	

}
