package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;


@Getter
public enum ETransactionType {

    STOCK_IN("STOCK_IN"),
    STOCK_OUT("STOCK_OUT");

    private final String name;

    ETransactionType(String name) {
        this.name = name;
    }
}
