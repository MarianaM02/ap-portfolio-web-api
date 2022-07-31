package com.apportfolio.core.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDTO implements Serializable{

	private static final long serialVersionUID = -3397197121657291754L;
	private Long id;
	@NotBlank(message = "Campo título de proyecto no puede ser nulo ni estar vacío")
	private String projectTitle;
	@NotBlank(message = "Campo descripción no puede ser nulo ni estar vacío")
	private String description;
	private String pictureUrl;
	@NotBlank(message = "Campo URL no puede ser nulo ni estar vacío")
	private String projectUrl;
	@NotNull
	private Long userId;

}
