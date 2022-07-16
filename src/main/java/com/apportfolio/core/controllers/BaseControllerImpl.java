package com.apportfolio.core.controllers;

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

import com.apportfolio.core.entities.Base;
import com.apportfolio.core.services.BaseServiceImpl;

@RestController
public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>>
		implements BaseController<E, Long> {

	@Autowired
	protected S servicio;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
	}

	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
	}

	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody E entity) {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
	}

}
