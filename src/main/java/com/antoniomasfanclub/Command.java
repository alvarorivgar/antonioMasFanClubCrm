package com.antoniomasfanclub;

public enum Command {
    NEW_LEAD("new lead"),
    LIST_LEADS("list leads"),
    CONVERT("convert"),
    QUIT("quit");


    private final String value;

    Command(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
