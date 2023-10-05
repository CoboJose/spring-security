package com.security.cobo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.security.cobo.controller.UserController.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	private static final String secretKey = "mySecretKey";
    
    public String getJWTToken(User user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(user.user())
				.claim("authorities",
				grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.claim("email", user.email())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (3600 * 1000)))
				.signWith(SignatureAlgorithm.HS256,
						secretKey.getBytes()).compact();

		return  token;
	}

	public String refreshToken(String token) {
		String user = "";
		String email = "";

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
			user = claims.getBody().get("sub").toString();
			email = claims.getBody().get("email").toString();

		} catch (ExpiredJwtException expiredJwtException) {
			Claims claims = expiredJwtException.getClaims();
			user = claims.get("sub").toString();
			email = claims.get("email").toString();
		} catch (Exception e) {
			System.err.println("Invalid token");
		}

		if (!user.isEmpty() && !email.isEmpty()) {
			return this.getJWTToken(new User(user, email, ""));
		}

		return "";
	}
}
