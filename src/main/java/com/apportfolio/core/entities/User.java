package com.apportfolio.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private static final long serialVersionUID = -3451269288249446390L;
	@Id
	private Long id;
	@Column(name="email")
    private String email;
    @Column(name = "pass")
    private String pass;
}
