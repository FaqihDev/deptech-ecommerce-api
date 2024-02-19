package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EUserRole {
    USER("User"),ADMIN("Admin"),PRINCIPLE("Principle");

    private final String name;

    EUserRole(String name) {
        this.name = name;
    }

}
