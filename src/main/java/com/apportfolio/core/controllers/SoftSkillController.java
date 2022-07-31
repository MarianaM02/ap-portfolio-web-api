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

import com.apportfolio.core.models.dto.SkillDTO;
import com.apportfolio.core.models.entities.SoftSkill;
import com.apportfolio.core.services.SoftSkillServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/soft-skill")
@RequiredArgsConstructor
public class SoftSkillController {
	private final UserServiceImpl userService;
	private final SoftSkillServiceImpl softSkillService;
	private final ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<SkillDTO> dtoList = softSkillService.findAll().stream().map(e -> modelMapper.map(e, SkillDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(softSkillService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		SkillDTO dto = modelMapper.map(softSkillService.findById(id), SkillDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody SkillDTO dto) {
		SoftSkill entity = modelMapper.map(dto, SoftSkill.class);
		// TODO many to many creacion
		// User user = userService.findById(dto.getUserId());
		dto = modelMapper.map(softSkillService.save(entity), SkillDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SkillDTO dto) {
		SoftSkill entity = modelMapper.map(dto, SoftSkill.class);
		dto = modelMapper.map(softSkillService.update(id, entity), SkillDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		SkillDTO dto = modelMapper.map(softSkillService.delete(id), SkillDTO.class);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getAllByUser(@PathVariable Long id) {
		List<SkillDTO> dtoList = softSkillService.findByUser(userService.findById(id)).stream().map(e -> modelMapper.map(e, SkillDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}

}
