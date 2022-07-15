package com.apportfolio.core.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @ToString
public class Error {

	@NonNull
	private HttpStatus estado;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyyy hh:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();
	@NonNull
	private Object mensajes;
	
}
