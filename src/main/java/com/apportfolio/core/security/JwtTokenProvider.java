package com.apportfolio.core.security;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	private final UserServiceImpl userService;
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";

	public String generateToken(String email) {
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new DataNotFoundException("No se encontró usuario " + email));
		String formatedRoles = user.getRoles().stream().map(r -> r.getRole().toString())
				.collect(Collectors.joining(", "));

		return JWT.create().withSubject(Long.toString(user.getId()))
				.withExpiresAt(Instant.now().plusMillis(expiration))
				.withClaim("roles", formatedRoles)
				.withClaim("username", user.getEmail())
				.sign(getAlgorithm());
	}

	public String generateRrefreshToken(String email) {
		//TODO refresh token
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new DataNotFoundException("No se encontró usuario " + email));
		return JWT.create().withSubject(user.getEmail())
				.withExpiresAt(Instant.now().plusMillis(expiration * 3))
				.sign(getAlgorithm());
	}

	public Map<String, String> refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(TOKEN_HEADER);
		if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
			try {
				String refreshToken = authorizationHeader.substring(TOKEN_PREFIX.length());
				Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				Long id = Long.getLong(decodedJWT.getSubject());
				User user = userService.findById(id);
				log.info(user.toString());
				String accessToken = JWT.create().withSubject(user.getEmail())
						.withExpiresAt(Instant.now().plusMillis(expiration))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream()
								.map(role -> role.getRole().toString()).collect(Collectors.toList()))
						.sign(algorithm);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshToken);
				return tokens;

			} catch (Exception e) {
				log.error("Error logging in: {}", e.getMessage());
				e.printStackTrace();
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());

				Map<String, String> error = new HashMap<>();
				error.put("error", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				try {
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		} else {
			throw new RuntimeException("Falta Refresh Token");
		}
		return null;
	}

	public Long getId(String token) {
		DecodedJWT jwtDecodificado = decode(token);
		return Long.parseLong(jwtDecodificado.getSubject());
	}

	public List<String> getRoles(String token) {
		DecodedJWT jwtDecodificado = decode(token);
		return jwtDecodificado.getClaim("roles").asList(String.class);
	}

	public String getEmail(String token) {
		DecodedJWT jwtDecodificado = decode(token);
		return jwtDecodificado.getClaim("username").asString();
	}

	public boolean isValid(String token) {
		try {
			decode(token);
			return true;
		} catch (JWTDecodeException e) {
			log.info("Token malformado: " + e.getMessage());
		} catch (TokenExpiredException e) {
			log.info("El token ha expirado: " + e.getMessage());
		} catch (SignatureVerificationException e) {
			log.info("Error en la firma del token JWT: " + e.getMessage());
		} catch (InvalidClaimException e) {
			log.info("JWT claims vacío");
		} catch (AlgorithmMismatchException e) {
			log.info("Token JWT no soportado: " + e.getMessage());
		}
		return false;
	}

	private Algorithm getAlgorithm() {
		return Algorithm.HMAC256(secret.getBytes());
	}

	private DecodedJWT decode(String token) {
		return JWT.require(getAlgorithm()).build().verify(token);
	}

}
