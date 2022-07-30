package com.apportfolio.core.models;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Mail {
	private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, String> model;

    public Mail() {
        model = new HashMap<>();
    }

}
