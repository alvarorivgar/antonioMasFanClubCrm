package com.antoniomasfanclub;

public enum Colours {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    BACKGROUND_BLACK("\u001B[40m"),
    BACKGROUND_RED("\u001B[41m"),
    BACKGROUND_GREEN("\u001B[42m\u001B[30m"),
    BACKGROUND_YELLOW("\u001B[43m\u001B[30m"),
    BACKGROUND_BLUE("\u001B[44m\u001B[30m"),
    BACKGROUND_PURPLE("\u001B[45m"),
    BACKGROUND_CYAN("\u001B[46m\u001B[30m"),
    BACKGROUND_WHITE("\u001B[47m\u001B[30m");

    private final String value;

    Colours(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
