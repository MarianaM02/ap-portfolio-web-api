package com.apportfolio.core.entities;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= "hard_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HardSkill extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5783592518442754365L;
	@Column(name="skill_name")
    private String skillName;
	@Column(name = "picture_url")
    private String pictureUrl;
	
}
