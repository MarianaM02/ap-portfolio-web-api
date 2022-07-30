package com.apportfolio.core.configuration;

import java.io.IOException;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	// TODO app variables
	// @Value("${app.jwt.expiration}")
	private Long expiration = 900000l;
	// @Value("${app.jwt.secret}")
	private String secret = "mySecret";

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {


		try {
			String username, password;
			Map<String, String> requestMap;
			requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			username = requestMap.get("username");
			password = requestMap.get("password");
			// username = request.getParameter("username");
			// String password = request.getParameter("password");
			log.info("Username es: {}", username);
			UsernamePasswordAuthenticationToken authtenticationToken = 
					new UsernamePasswordAuthenticationToken(username, password);
			return authenticationManager.authenticate(authtenticationToken);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		// TODO mover secret

		Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
		String accessToken = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(Instant.now().plusMillis(expiration)).withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		String refreshToken = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(Instant.now().plusMillis(expiration * 3)).withIssuer(request.getRequestURL().toString())
				.sign(algorithm);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
