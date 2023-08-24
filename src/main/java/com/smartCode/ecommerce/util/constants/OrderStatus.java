package com.smartCode.ecommerce.util.constants;

public enum OrderStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    COLLECTED("COLLECTED"),
    ON_THE_WAY("ON_THE_WAY"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
