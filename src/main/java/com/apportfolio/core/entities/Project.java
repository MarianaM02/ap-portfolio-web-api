package com.apportfolio.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {
	
	@Column(name = "project_title")
    private String projectTitle;
	@Column(name = "description")
	private String description;
	@Column(name = "picture_url")
    private String pictureUrl;
	@Column(name = "project_url")
	private String projectUrl;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;
	
}
