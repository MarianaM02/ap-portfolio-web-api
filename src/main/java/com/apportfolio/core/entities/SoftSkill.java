package com.apportfolio.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "soft_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SoftSkill extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1415609163120946411L;
	@Column(name="skill_name")
    private String skillName;
	
}
