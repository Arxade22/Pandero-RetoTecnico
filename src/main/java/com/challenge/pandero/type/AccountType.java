package com.challenge.pandero.type;

public enum AccountType {
    BASIC(1),
    STANDARD(2),
    PREMIUM(3);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
