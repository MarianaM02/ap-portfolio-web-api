package com.apportfolio.core.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkillDTO implements Serializable{

	private static final long serialVersionUID = -3800810059146675808L;
	private Long id;
	@NotBlank(message = "Campo nombre de skill no puede ser nulo ni estar vacío")
    private String skillName;
    private String pictureUrl;
    @NotNull
    private Long userId;

}
