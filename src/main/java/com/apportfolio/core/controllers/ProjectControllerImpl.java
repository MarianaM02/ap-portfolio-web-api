package com.apportfolio.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.models.dto.ProjectDTO;
import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.services.ProjectServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/project")
@RequiredArgsConstructor
public class ProjectControllerImpl {

	@Autowired
	protected ProjectServiceImpl servicio;
	private final ModelMapper modelMapper;

	public ResponseEntity<?> getAll() {
		List<ProjectDTO> dtoList = servicio.findAll().stream().map(e -> modelMapper.map(e, ProjectDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
	}

	public ResponseEntity<?> getOne(@PathVariable Long id) {
		ProjectDTO dto = modelMapper.map(servicio.findById(id), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public ResponseEntity<?> save(@Valid @RequestBody ProjectDTO dto) {
		Project entity = modelMapper.map(dto, Project.class);
		dto = modelMapper.map(servicio.save(entity), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProjectDTO dto) {
		Project entity = modelMapper.map(dto, Project.class);
		dto = modelMapper.map(servicio.update(id, entity), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {
		ProjectDTO dto = modelMapper.map(servicio.delete(id), ProjectDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}
}
