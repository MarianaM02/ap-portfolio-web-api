package com.apportfolio.core.models.entities;

import lombok.Getter;

/**
 * The enum Role role.
 */
@Getter
public enum RoleName {

    /**
     * Role admin authority role.
     */
    ROLE_ADMIN("ROLE_ADMIN"),

    /**
     * Role user authority role.
     */
    ROLE_USER("ROLE_USER");
	
	private String name;

	RoleName(String name) {
		this.name = name;
	}
}

