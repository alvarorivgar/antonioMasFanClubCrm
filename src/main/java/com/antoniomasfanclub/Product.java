package com.antoniomasfanclub;

public enum Product {

    HYBRID("hybrid"),
    FLATBED("flatbed"),
    BOX("box");

    private final String value;

    Product(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
