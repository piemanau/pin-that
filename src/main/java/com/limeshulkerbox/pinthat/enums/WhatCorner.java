package com.limeshulkerbox.pinthat.enums;

public enum WhatCorner {
    TOPL("Top Left", 0),
    TOPR("Top Right", 1),
    BOTTOML("Bottom Left", 2),
    BOTTOMR("Bottom Right", 3);

    private final String name;
    public final int value;

    WhatCorner(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}