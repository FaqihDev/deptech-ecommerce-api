package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EUserRole {
    USER("USER"),
    ADMIN("ADMIN"),
    PRINCIPLE("PRINCIPLE");

    private final String name;

    EUserRole(String name) {
        this.name = name;
    }

}