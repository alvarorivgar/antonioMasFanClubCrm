package com.antoniomasfanclub;

public class Lead extends Person {
    private static int generatedLeads = 0;

    public Lead() {
        super(generateId(), "", "", "", "");
    }

    public Lead(String name, String phoneNumber, String email, String companyName) {
        super(generateId(), name, phoneNumber, email, companyName);
    }

    private static int generateId(){
        return ++generatedLeads;
    }
}
