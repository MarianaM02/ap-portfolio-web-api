package com.apportfolio.core.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Experience extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8317977607038984760L;
	@Column(name = "title")
    private String title;
	@Column(name = "place")
    private String place;
	@Column(name = "description")
	private String description;
	@Column(name = "start_date")
    private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "picture_url")
    private String pictureUrl;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;
	
}
