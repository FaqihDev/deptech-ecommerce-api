package com.jamsirat.atmapi.statval.enumeration;

import lombok.Getter;

@Getter
public enum EDapuanLevel {
    KELOMPOK("KELOMPOK"),DESA("DESA"),DAERAH("DAERAH");

    public final String name;

    EDapuanLevel(String name) {
        this.name = name;
    }
}
