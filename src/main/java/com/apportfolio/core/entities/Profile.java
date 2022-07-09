package com.apportfolio.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
// @Audited
public class Profile extends Base {
	private static final long serialVersionUID = 3407283444738478974L;
	@Id
	private Long id;
	@Column(name="name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "about", length = 2000)
    private String about;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;
    
}
