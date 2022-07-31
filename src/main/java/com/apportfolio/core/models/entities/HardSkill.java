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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_hard_skills", joinColumns = {@JoinColumn(name="skill_id")}, inverseJoinColumns = { @JoinColumn(name = "user_id")})
	@JsonIgnore
	private List<User> user= new ArrayList<>();
	
}
