package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;

@Getter
public enum EAccessType {
    ADMIN_ACCESS("ADMIN_ACCESS"),
    PRINCIPLE_ACCESS("PRINCIPLE_ACCESS"),
    USER_ACCESS("USER_ACCESS");

    private String name;

    EAccessType(String name) {
        this.name = name;
    }
}