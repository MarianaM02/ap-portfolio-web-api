package com.apportfolio.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.models.dto.BaseDTO;
import com.apportfolio.core.models.entities.Base;
import com.apportfolio.core.services.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public abstract class BaseControllerImpl<D extends BaseDTO, E extends Base, S extends BaseServiceImpl<E, Long>>{

	@Autowired
	protected S servicio;
	private final ModelMapper modelMapper;
	private final Class<D> classDto;
	private final Class<E> classEntity;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<D> dtos = servicio.findAll()
				.stream().map(e -> modelMapper.map(e, classDto))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		// TODO pageable
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id){
		D dto = modelMapper.map(servicio.findById(id), classDto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody D dto) {
		E entity = modelMapper.map(dto, classEntity);
		dto = modelMapper.map(servicio.save(entity), classDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody D dto) {
		E entity = modelMapper.map(dto, classEntity);
		dto = modelMapper.map(servicio.update(id, entity), classDto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		D dto = modelMapper.map(servicio.delete(id), classDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
	}

}
