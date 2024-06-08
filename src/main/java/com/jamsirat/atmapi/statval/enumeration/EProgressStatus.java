package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;

@Getter
public enum  EProgressStatus {

    AVAILABLE("AVAILABLE"),CONTINUE("CONTINUE"),PROGRESS("PROGRESS"),MATCHED("MATCHED");

    private final String name;

    EProgressStatus(String name) {
        this.name = name;
    }
}
