package com.apportfolio.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profile extends Base {
	private static final long serialVersionUID = 3407283444738478974L;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	@Column(name="name")
    private String name;
	@NotBlank(message = "Campo apellido no puede ser nulo ni estar vacío")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "picture_url")
    private String pictureUrl;    
    @Column(name = "title")
    private String title;
    @Column(name = "location")
    private String location;
    @Size(max=2000, message="La sección supera el máximo de caracteres")
    @Column(name = "about", length = 2000)
    private String about;    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;
    
}
