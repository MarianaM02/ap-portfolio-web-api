package com.apportfolio.core.models.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
    private String email;
    private Set<String> roles = new HashSet<>();
    private String token;
}
