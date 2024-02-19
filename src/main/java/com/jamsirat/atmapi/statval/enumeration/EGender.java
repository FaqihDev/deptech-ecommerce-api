package com.jamsirat.atmapi.statval.enumeration;


import lombok.Getter;

@Getter
public enum EGender {

    MALE("M"),FEMALE("F");

    private final String name;

    EGender(String name) {
        this.name = name;
    }
}
