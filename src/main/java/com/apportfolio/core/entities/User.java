package com.apportfolio.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "Campo email no puede ser nulo ni estar vacío")
	@Email(message = "El email debe ser válido")
	@Column(name="email")
    private String email;
	@Size(min = 6, max = 20, message= "La contraseña debe tener entre 6 y 20 caracteres")
    @Column(name = "pass")
    private String pass;
}
