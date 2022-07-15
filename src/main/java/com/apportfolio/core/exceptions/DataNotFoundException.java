package com.apportfolio.core.exceptions;

import java.io.Serializable;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1595639413816612965L;

	public DataNotFoundException() {
		super("No encontrado");
	}
	
	public DataNotFoundException(Serializable id) {
		super("No encontrado. ID: " + id);
	}
	
}
