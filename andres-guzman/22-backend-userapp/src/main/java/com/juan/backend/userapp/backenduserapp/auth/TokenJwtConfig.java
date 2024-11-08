package com.juan.backend.userapp.backenduserapp.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {

	// public static final String SECRET_KEY =
	// "algun_token_con_alguna_frase_secreta";
	// public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public final static SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
	// public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("algun_token_con_alguna_frase_secreta".getBytes(StandardCharsets.UTF_8));
	public static final String PREFIX_TOKEN = "Bearer ";
	public static final String HEADER_AUTHORIZATION = "Authorization";

}
