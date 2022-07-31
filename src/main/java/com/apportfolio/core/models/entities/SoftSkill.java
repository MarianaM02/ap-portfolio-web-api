package com.apportfolio.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name="skill_name")
    private String skillName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_soft_skills", joinColumns = {@JoinColumn(name="skill_id")}, inverseJoinColumns = { @JoinColumn(name = "user_id")})
	@JsonIgnore
	private List<User> user= new ArrayList<>();
	
}
