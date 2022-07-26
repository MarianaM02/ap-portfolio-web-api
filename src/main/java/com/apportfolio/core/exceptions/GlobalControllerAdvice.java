package com.apportfolio.core.exceptions;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.MappingException;
import org.modelmapper.ConfigurationException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Error> handleDataNotFoundException(DataNotFoundException ex){
		Error error = new Error(HttpStatus.NOT_FOUND, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex){
		Error error = new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ConfigurationException.class)
	public ResponseEntity<Error> ConfigurationException(ConfigurationException ex){
		Error error = new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(MappingException.class)
	public ResponseEntity<Error> MappingException(MappingException ex){
		Error error = new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Error> handleIOException(IOException ex){
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = new Error(status, ex.getMessage());
		log.error(error.getMensajes().toString());
		return ResponseEntity.status(status).headers(headers).body(error);
	}
	
	
}
