package com.example.demo.Domain.Enums;


public enum StateEnum {
    RIO_GRANDE_DO_SUL("Rio Grande do Sul"),
    SAO_PAULO("Sao Paulo"),
    PERNAMBUCO("Pernambuco");

    private final String displayName;

    StateEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}