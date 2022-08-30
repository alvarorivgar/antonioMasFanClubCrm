package com.antoniomasfanclub;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final int id;
    private int employeeCount;
    private Industry industry;
    private String city;
    private String country;
    private final Map<Integer, Contact> contactList;
    private final Map<Integer, Opportunity> opportunityList;
    private static int generatedInstances = 0;

    //Constructor
    public Account() {
        this.id = autogenerateId();
        this.contactList = new HashMap<>();
        this.opportunityList = new HashMap<>();
    }

    public Account(Industry industry, int employeeCount, String city, String country) {
        this.id = autogenerateId();
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = new HashMap<>();
        this.opportunityList = new HashMap<>();
    }

    //Methods
    public void addContact(Integer key, Contact contact) {
        contactList.put(key, contact);
    }

    public void removeContact(Integer key) {
        contactList.remove(key);
    }

    public Contact getContact(Integer key) {
        return contactList.get(key);
    }

    public void addOpportunity(Integer key) {
        opportunityList.remove(key);
    }

    public void removeOpportunity(Integer key) {
        opportunityList.remove(key);
    }

    public Opportunity getOpportunity(Integer key) {
        return opportunityList.get(key);
    }

    //Getters & Setters
    public int getId() {
        return this.id;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        if (employeeCount < 1) throw new IllegalArgumentException("Accounts should have 1 employee at the very least");
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        if (city == null || city.trim().length() < 3)
            throw new IllegalArgumentException("🏬 City name should be at least " + CLI.colourString(Colours.YELLOW, "3 characters") + " long ");
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().length() < 3)
            throw new IllegalArgumentException("🇺🇳 Country name should be at least " + CLI.colourString(Colours.YELLOW, "3 characters") + " long ");
        this.country = country;
    }

    public Map<Integer, Contact> getContactList() {
        return contactList;
    }

    public Contact getContact(int id) {
        if (contactList.get(id) == null) throw new IllegalArgumentException("No contacts found with ID " + id);
        return contactList.get(id);
    }

    public void addContact(Contact contact) {
        this.contactList.put(contact.getId(), contact);
    }

    public Opportunity getOpportunity(int id) {
        if (opportunityList.get(id) == null) throw new IllegalArgumentException("No opportunities found with ID " + id);
        return opportunityList.get(id);
    }

    public void addOpportunity(Opportunity opportunity) {
        this.opportunityList.put(opportunity.getId(), opportunity);
    }

    public Map<Integer, Opportunity> getOpportunityList() {
        return opportunityList;
    }


    private int autogenerateId() {
        return ++generatedInstances;
    }

    @Override
    public String toString() {
        return CLI.colourString(Colours.BACKGROUND_CYAN, " 🆔 " + this.getId() + " ") + " Industry: " + this.getIndustry().toString().toLowerCase() + "; 🏬 Located in: " + this.getCity() + ", " + this.getCountry() + "  👔 Employee count: " + this.getEmployeeCount();
    }
}
