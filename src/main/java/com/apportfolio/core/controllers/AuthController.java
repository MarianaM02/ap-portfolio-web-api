package com.apportfolio.core.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.models.dto.UserCreateDTO;
import com.apportfolio.core.models.dto.UserDTO;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.security.JwtTokenProvider;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtProvider;
	private final UserServiceImpl userService;
	private final ModelMapper modelMapper;
	// private final MailService emailServicio;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCreateDTO userCreateDTO) {
		log.info("Logueando usuario {}", userCreateDTO.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userCreateDTO.getEmail(), userCreateDTO.getPass()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDTO userDTO  = modelMapper.map(userService.findByEmail(userCreateDTO.getEmail()), UserDTO.class);
		userDTO.setToken(jwtProvider.generateToken(userCreateDTO.getEmail()));
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registro(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		if (!userCreateDTO.getPass().equals(userCreateDTO.getRepeatedPass())) {
			throw new RuntimeException("Las contraseñas no coinciden");
		}
		if (userService.existsByEmail(userCreateDTO.getEmail())) {
			throw new RuntimeException("El usuario ya existe: "+ userCreateDTO.getEmail());
		}

		log.info("Creando el usuario {}", userCreateDTO.getEmail());
		User user = modelMapper.map(userCreateDTO, User.class);
		UserDTO userDTO = modelMapper.map(userService.save(user), UserDTO.class);
		userDTO.setToken(jwtProvider.generateToken(user.getEmail()));

		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}

}
