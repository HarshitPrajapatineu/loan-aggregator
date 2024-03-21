package com.quikloan.server.authenticator;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quikloan.server.dto.UserSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

public class JwtGenerator implements IJwtGenerator {
	@Value("${jwt.secret}")
	  private String secret;
	  @Value("${app.jwttoken.message}")
	  private String message;

	  @Override
	  public String generateToken(UserSession user) {
	    String jwtToken="";
	    jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
	    Map<String, String> jwtTokenGen = new HashMap<>();
	    jwtTokenGen.put("token", jwtToken);
	    jwtTokenGen.put("message", message);
	    return jwtToken;
	  }
}
