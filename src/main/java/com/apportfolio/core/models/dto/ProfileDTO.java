package com.apportfolio.core.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = -6055282818271325605L;
	private Long id;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
    private String name;
	@NotBlank(message = "Campo apellido no puede ser nulo ni estar vacío")
    private String lastName;
    private String pictureUrl;   
    private String title;
    private String location;
    @Size(max=2000, message="La sección supera el máximo de caracteres")
    private String about;
    @NotNull
    private Long userId;
    
}
