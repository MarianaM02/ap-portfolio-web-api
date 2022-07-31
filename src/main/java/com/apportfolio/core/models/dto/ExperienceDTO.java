package com.apportfolio.core.models.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExperienceDTO implements Serializable{

	private static final long serialVersionUID = 5253989018929473125L;
	private Long id;
	@NotBlank(message = "Campo título no puede ser nulo ni estar vacío")
	private String title;
    private String place;
	private String description;
	@NotBlank(message = "Campo fecha de comienzo no puede ser nulo ni estar vacío")
    private LocalDate startDate;
	private LocalDate endDate;
    private String pictureUrl;
    @NotNull
    private Long userId;
}
