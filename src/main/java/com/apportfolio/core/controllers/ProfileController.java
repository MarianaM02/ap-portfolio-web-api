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

import com.apportfolio.core.models.dto.ProfileDTO;
import com.apportfolio.core.models.entities.Profile;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.ProfileServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
	private final UserServiceImpl userService;
	private final ProfileServiceImpl profileService;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		log.info("Trayendo todos los perfiles");
		List<ProfileDTO> dtoList = profileService.findAll().stream().map(e -> modelMapper.map(e, ProfileDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(profileService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		log.info("Trayendo el perfil {}", id);
		ProfileDTO dto = modelMapper.map(profileService.findById(id), ProfileDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody ProfileDTO dto) {
		log.info("Creando el perfil de {}", dto.getName());
		Profile entity = modelMapper.map(dto, Profile.class);
		User user = userService.findById(dto.getUserId());
		entity.setUser(user);
		dto = modelMapper.map(profileService.save(entity), ProfileDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProfileDTO dto) {
		log.info("Actualizando el perfil {}", id);
		Profile entity = profileService.findById(id);
		entity.setName(dto.getName());
		entity.setLastName(dto.getLastName());
		entity.setAbout(dto.getAbout());
		entity.setLocation(dto.getLocation());
		entity.setPictureUrl(dto.getPictureUrl());
		entity.setTitle(dto.getTitle());
		dto = modelMapper.map(profileService.update(id, entity), ProfileDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("Borrando el perfil {}", id);
		ProfileDTO dto = modelMapper.map(profileService.delete(id), ProfileDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getByUser(@PathVariable Long id) {
		log.info("Trayendo el perfil del usuario {}", id);
		ProfileDTO dto = modelMapper.map(profileService.findByUser(userService.findById(id)), ProfileDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
}
