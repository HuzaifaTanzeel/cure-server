package com.example.demo.CommonFramework.Enumerators;

public enum ContactPointType {
    PHONE(3301),
    FAX(3302),
    EMAIL(3303);

    private final int code;

    ContactPointType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
