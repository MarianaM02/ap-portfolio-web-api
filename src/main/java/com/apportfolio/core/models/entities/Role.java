package com.apportfolio.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends Base{

	@Column(name="name")
	private String name;
	
}
