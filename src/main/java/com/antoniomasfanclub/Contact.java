package com.antoniomasfanclub;

public class Contact extends Person{

    public Contact(Lead lead) {
        super(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
    }
}
