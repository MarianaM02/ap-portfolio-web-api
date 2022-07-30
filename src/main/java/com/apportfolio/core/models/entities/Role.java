package com.apportfolio.core.models.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Role extends Base {

	@Column(name="name")
	@Enumerated(EnumType.STRING)
    @NaturalId
	private RoleName role;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> userList = new HashSet<>();
	
	public Role(RoleName role) {
        this.role = role;
    }
	
	public boolean isAdminRole() {
        return null != this && this.role.equals(RoleName.ROLE_ADMIN);
    }
	
	@Override
	public String toString() {
		return this.role.getName();
	}
	
}
