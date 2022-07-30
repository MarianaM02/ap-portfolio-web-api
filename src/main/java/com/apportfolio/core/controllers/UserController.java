package com.apportfolio.core.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.models.dto.UserDTO;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	@Autowired
	private UserServiceImpl servicio;
	private final ModelMapper modelMapper;
	private Long expiration = 900000l;
	private String secret = "mySecret";

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		log.info("Trayendo todos los usuarios");
		List<UserDTO> dtoList = servicio.findAll().stream().map(e -> modelMapper.map(e, UserDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		log.info("Trayendo el usuario {}", id);
		UserDTO dto = modelMapper.map(servicio.findById(id), UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody UserDTO dto) {
		log.info("Creando el usuario {}", dto.getEmail());
		User entity = modelMapper.map(dto, User.class);
		dto = modelMapper.map(servicio.save(entity), UserDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
		log.info("Actualizando el usuario {}", id);
		User entity = modelMapper.map(dto, User.class);
		dto = modelMapper.map(servicio.update(id, entity), UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("Borrando el usuario {}", id);
		UserDTO dto = modelMapper.map(servicio.delete(id), UserDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				String username = decodedJWT.getSubject();
				User user = servicio.findByEmail(username).orElseThrow();
				log.info(user.toString());
				String accessToken = JWT.create().withSubject(user.getEmail())
						.withExpiresAt(Instant.now().plusMillis(expiration))
						.withIssuer(request.getRequestURL().toString()).withClaim("roles", user.getRoles().stream()
								.map(role -> role.getRole().toString()).collect(Collectors.toList()))
						.sign(algorithm);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception e) {
				log.error("Error logging in: {}", e.getMessage());
				e.printStackTrace();
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());

				Map<String, String> error = new HashMap<>();
				error.put("error", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}

		} else {
			throw new RuntimeException("Falta Refresh Token");
		}
	}

}
