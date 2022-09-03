package com.antoniomasfanclub;

public enum Command {
    NEW_LEAD("new lead"),
    LOOKUP("lookup lead"),
    LIST_LEADS("list leads"),
    CONVERT("convert"),
    CLOSED_LOST("closed_lost"),
    CLOSED_WON("closed_won"),
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
