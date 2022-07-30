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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.models.dto.ExperienceDTO;
import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.services.JobExperienceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/job-experience")
@RequiredArgsConstructor
public class JobExperienceController {

	@Autowired
	protected JobExperienceServiceImpl servicio;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<ExperienceDTO> dtoList = servicio.findAll().stream().map(e -> modelMapper.map(e, ExperienceDTO.class))
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
		ExperienceDTO dto = modelMapper.map(servicio.findById(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody ExperienceDTO dto) {
		JobExperience entity = modelMapper.map(dto, JobExperience.class);
		dto = modelMapper.map(servicio.save(entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ExperienceDTO dto) {
		JobExperience entity = modelMapper.map(dto, JobExperience.class);
		dto = modelMapper.map(servicio.update(id, entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		ExperienceDTO dto = modelMapper.map(servicio.delete(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

}