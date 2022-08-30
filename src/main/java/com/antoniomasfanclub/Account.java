package com.antoniomasfanclub;

import java.security.Key;
import java.util.List;
import java.util.Map;

public class Account {
    private int id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private Map<Integer,Contact> contactList;
    private Map<Integer, Opportunity> opportunityList;

    //Constructor
    public Account(int id, Industry industry, int employeeCount, String city, String country, Map<Integer,Contact> contactList, Map<Integer,Opportunity> opportunityList) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }
//Methods
    public void addContact(Integer key, Contact contact){
        contactList.put(key, contact);
    }

    public void removeContact(Integer key){
        contactList.remove(key);
    }

    public void getContact(Integer key){
        System.out.println(contactList.get(key));
    }
    public void addOpportunity(Integer key){
        opportunityList.remove(key);
    }

    public void removeOpportunity(Integer key){
        opportunityList.remove(key);
    }

    public void getOpportunity(Integer key, Opportunity opportunity){
        System.out.println(opportunityList.get(key));
    }

//Getters & Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<Integer, Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Map<Integer, Contact> contactList) {
        this.contactList = contactList;
    }

    public Map<Integer, Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(Map<Integer, Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

}
