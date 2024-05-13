package com.example.demo.CommonFramework.Enumerators;

public enum PracticeRole {
    OWNER(3701, "OWNER"),
    CO_PRACTITIONER(3702, "CO-PRACTITIONER");

    private final int code;
    private final String description;

    PracticeRole(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

