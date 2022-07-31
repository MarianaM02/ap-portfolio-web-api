package com.apportfolio.core.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleDTO implements Serializable{

	private static final long serialVersionUID = 3094964119578597546L;
	private Long id;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	private String roleName;

}
