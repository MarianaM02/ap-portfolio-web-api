package com.apportfolio.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "hard_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HardSkill extends Base {
	
	@Column(name="skill_name")
    private String skillName;
	@Column(name = "picture_url")
    private String pictureUrl;
	
}
