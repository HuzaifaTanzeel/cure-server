package com.example.demo.CommonFramework.Enumerators;

public enum AddressType {
    POSTAL(3901),
    PHYSICAL(3902),
    BOTH(3903);

    private final int code;

    AddressType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}

