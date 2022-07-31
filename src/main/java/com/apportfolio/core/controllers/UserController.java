package com.apportfolio.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.apportfolio.core.models.dto.UserCreateDTO;
import com.apportfolio.core.models.dto.UserDTO;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserServiceImpl userService;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		log.info("Trayendo todos los usuarios");
		List<UserDTO> dtoList = userService.findAll().stream().map(e -> modelMapper.map(e, UserDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		log.info("Trayendo el usuario {}", id);
		UserDTO dto = modelMapper.map(userService.findById(id), UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getOneByEmail(@PathVariable String email) {
		log.info("Trayendo el usuario {}", email);
		UserDTO dto = modelMapper.map(userService.findByEmail(email), UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody UserCreateDTO dto) {
		log.info("Creando el usuario {}", dto.getEmail());
		User entity = modelMapper.map(dto, User.class);
		dto = modelMapper.map(userService.save(entity), UserCreateDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserCreateDTO dto) {
		log.info("Actualizando el usuario {}", id);
		User entity = userService.findById(id);
		entity.setEmail(dto.getEmail());
		entity.setPass(dto.getPass());
		dto = modelMapper.map(userService.update(id, entity), UserCreateDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("Borrando el usuario {}", id);
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
