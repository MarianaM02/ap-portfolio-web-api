package com.apportfolio.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.apportfolio.core.models.dto.ExperienceDTO;
import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.services.JobExperienceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/job-experience")
@RequiredArgsConstructor
public class JobExperienceControllerImpl {

	@Autowired
	protected JobExperienceServiceImpl servicio;
	private final ModelMapper modelMapper;

	public ResponseEntity<?> getAll() {
		List<ExperienceDTO> dtoList = servicio.findAll().stream().map(e -> modelMapper.map(e, ExperienceDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
	}

	public ResponseEntity<?> getOne(@PathVariable Long id) {
		ExperienceDTO dto = modelMapper.map(servicio.findById(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public ResponseEntity<?> save(@RequestBody ExperienceDTO dto) {
		JobExperience entity = modelMapper.map(dto, JobExperience.class);
		dto = modelMapper.map(servicio.save(entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExperienceDTO dto) {
		JobExperience entity = modelMapper.map(dto, JobExperience.class);
		dto = modelMapper.map(servicio.update(id, entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {
		ExperienceDTO dto = modelMapper.map(servicio.delete(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

}
