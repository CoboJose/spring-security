package com.security.cobo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.security.cobo.controller.UserController.User;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {
    
    public String getJWTToken(User user) {
		String secretKey = "mySecretKey";
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
		String secretKey = "mySecretKey";

		Jwts.parser().setSigningKey(secretKey).parse(token);
		
		//System.err.println(jwt);

		return "";

	}
}
