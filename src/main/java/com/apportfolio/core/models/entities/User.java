package com.apportfolio.core.models.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends Base {

	@Column(name="email")
	@NaturalId
    private String email;
    @Column(name = "pass")
    private String pass;
	@Column(name = "roles")
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
        roles.add(role);
        role.getUserList().add(this);
    }

    public void addRoles(Collection<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUserList().remove(this);
    }
	
}
