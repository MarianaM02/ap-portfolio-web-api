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

import com.apportfolio.core.models.dto.ExperienceDTO;
import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.JobExperienceServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/job-experience")
@RequiredArgsConstructor
public class JobExperienceController {
	private final UserServiceImpl userService;
	private final JobExperienceServiceImpl jobExperienceService;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<ExperienceDTO> dtoList = jobExperienceService.findAll().stream().map(e -> modelMapper.map(e, ExperienceDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(jobExperienceService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		ExperienceDTO dto = modelMapper.map(jobExperienceService.findById(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody ExperienceDTO dto) {
		JobExperience entity = modelMapper.map(dto, JobExperience.class);
		User user = userService.findById(dto.getUserId());
		entity.setUser(user);
		dto = modelMapper.map(jobExperienceService.save(entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ExperienceDTO dto) {
		JobExperience entity = jobExperienceService.findById(id);
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setPlace(dto.getPlace());
		entity.setPictureUrl(dto.getPictureUrl());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		dto = modelMapper.map(jobExperienceService.update(id, entity), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		ExperienceDTO dto = modelMapper.map(jobExperienceService.delete(id), ExperienceDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getAllByUser(@PathVariable Long id) {
		List<ExperienceDTO> dtoList = jobExperienceService.findByUser(userService.findById(id)).stream().map(e -> modelMapper.map(e, ExperienceDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}
	
}
