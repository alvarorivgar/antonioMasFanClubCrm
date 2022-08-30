package com.antoniomasfanclub;

public class Contact extends Person{
    private static int generatedLeads = 0;

    public Contact(Lead lead) {
        super(generateId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
    }

    private static int generateId(){
        return ++generatedLeads;
    }
}
