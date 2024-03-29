package com.apportfolio.core.models.dto;


import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserCreateDTO implements Serializable{

	private static final long serialVersionUID = 5346757321500010420L;
	private Long id;
	@NotBlank(message = "Campo email no puede ser nulo ni estar vacío")
	@Email(message = "El email debe ser válido")
    private String email;
	@Size(min = 6, max = 20, message= "La contraseña debe tener entre 6 y 20 caracteres")
    private String pass;
	private String repeatedPass;
	
}
