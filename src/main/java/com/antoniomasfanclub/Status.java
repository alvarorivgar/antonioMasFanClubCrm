package com.antoniomasfanclub;

public enum Status {

    OPEN(Colours.YELLOW + "open" + Colours.RESET),
    CLOSED_WON(Colours.GREEN + "closed (won)" + Colours.RESET),
    CLOSED_LOST(Colours.RED + "closed (lost)" + Colours.RESET);

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
