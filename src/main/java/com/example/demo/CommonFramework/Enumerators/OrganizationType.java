package com.example.demo.CommonFramework.Enumerators;

public enum OrganizationType {
    PROV(3601, "Healthcare Provider"),
    DEPT(3602, "Hospital Department"),
    PRACTICE(3603, "Medical Practice");

    private final int code;
    private final String description;

    OrganizationType(int code, String description) {
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

