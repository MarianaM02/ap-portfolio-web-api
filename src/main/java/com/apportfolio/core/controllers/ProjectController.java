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

import com.apportfolio.core.models.dto.ProjectDTO;
import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.ProjectServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/project")
@RequiredArgsConstructor
public class ProjectController {
	private final UserServiceImpl userService;
	private final ProjectServiceImpl projectService;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<ProjectDTO> dtoList = projectService.findAll().stream().map(e -> modelMapper.map(e, ProjectDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		ProjectDTO dto = modelMapper.map(projectService.findById(id), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody ProjectDTO dto) {
		Project entity = modelMapper.map(dto, Project.class);
		User user = userService.findById(dto.getUserId());
		entity.setUser(user);
		dto = modelMapper.map(projectService.save(entity), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProjectDTO dto) {
		Project entity = projectService.findById(id);
		entity.setProjectTitle(dto.getProjectTitle());
		entity.setDescription(dto.getDescription());
		entity.setPictureUrl(dto.getPictureUrl());
		entity.setProjectUrl(dto.getProjectUrl());
		dto = modelMapper.map(projectService.update(id, entity), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		ProjectDTO dto = modelMapper.map(projectService.delete(id), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getAllByUser(@PathVariable Long id) {
		List<ProjectDTO> dtoList = projectService.findByUser(userService.findById(id)).stream().map(e -> modelMapper.map(e, ProjectDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}
}
