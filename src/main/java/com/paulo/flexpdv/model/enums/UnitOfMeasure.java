package com.paulo.flexpdv.model.enums;

public enum UnitOfMeasure {
    UN(1, "Unit"), KG(2, "Description"), L(3, "Liter");

    private final int code;
    private final String description;

    UnitOfMeasure(int code, String description) {
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
