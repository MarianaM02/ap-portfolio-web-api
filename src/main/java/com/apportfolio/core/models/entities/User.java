package com.apportfolio.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends Base {

	@Column(name="email")
    private String email;
    @Column(name = "pass")
    private String pass;
	@Column(name = "roles")
	@ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
	
}
