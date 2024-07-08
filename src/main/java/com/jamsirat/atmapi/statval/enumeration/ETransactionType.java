package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ETransactionType {

    STOCK_IN("STOCK_IN"),
    STOCK_OUT("STOCK_OUT");

    private String name;

    ETransactionType(String name) {
        this.name = name;
    }
}
