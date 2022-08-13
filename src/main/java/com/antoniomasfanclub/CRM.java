package com.antoniomasfanclub;

import java.util.HashMap;
import java.util.Map;

public class CRM {
    int leadCount;
    Map<Integer, Lead> leads;
    Map<Integer, Contact> contacts;
    Map<Integer, Opportunity> opportunities;
    Map<Integer, Account> accounts;

    public CRM() {
        this.leadCount = 0;
        this.leads = new HashMap<>();
        this.contacts = new HashMap<>();
        this.opportunities = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    public void addLead(Lead lead) {
        leads.put(lead.getId(), lead);
    }

    public Lead getLead(int id) {
        if (leads.get(id) == null) throw new IllegalArgumentException("No leads found with ID "+id);
        return leads.get(id);
    }

    public void deleteLead(int id) {
        if (leads.get(id) == null) throw new IllegalArgumentException("No leads found with ID "+id);
        leads.remove(id);
    }

//    TODO - Move this logic to CLI, using the getLead method instead
//    public void lookupLeadId(int id) {
//        if (leads.get(id) == null) throw new IllegalArgumentException("No leads found with ID "+id);
//        Lead lead = leads.get(id);
//        System.out.println("-------------------");
//        System.out.println("ID - "+lead.getId());
//        System.out.println("Name - "+lead.getName());
//        System.out.println("Phone number - "+lead.getPhoneNumber());
//        System.out.println("Email - "+lead.getEmail());
//        System.out.println("Company name - "+lead.getCompanyName());
//        System.out.println("-------------------");
//    }

  //  convertLeadToOpportunity(int leadId) ->  {new contact(lead), new opportunity(contact), delete lead}

    public void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    public Contact getContact(int id) {
        if (contacts.get(id) == null) throw new IllegalArgumentException("No contacts found with ID "+id);
        return contacts.get(id);
    }

    public void deleteContact(int id) {
        if (contacts.get(id) == null) throw new IllegalArgumentException("No contacts found with ID "+id);
        contacts.remove(id);
    }

    public void addOpportunity(Opportunity opportunity) {
        opportunities.put(opportunity.getId(), opportunity);
    }

    public Opportunity getOpportunity(int id) {
        if (opportunities.get(id) == null) throw new IllegalArgumentException("No opportunities found with ID "+id);
        return opportunities.get(id);
    }

    public void deleteOpportunity(int id) {
        if (opportunities.get(id) == null) throw new IllegalArgumentException("No opportunities found with ID "+id);
        opportunities.remove(id);
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account getAccount(int id) {
        if (accounts.get(id) == null) throw new IllegalArgumentException("No accounts found with ID "+id);
        return accounts.get(id);
    }

    public void deleteAccount(int id) {
        if (accounts.get(id) == null) throw new IllegalArgumentException("No accounts found with ID "+id);
        accounts.remove(id);
    }


    public int getLeadCount() {
        return leadCount;
    }

    public void increaseLeadCount(int leadCount) {
        this.leadCount += 1;
    }

    public void showLeads() {
        for (Integer id : leads.keySet()) {
            System.out.println("ID: " + id + " Name: " + leads.get(id));
        }
    }

    public Map<Integer, Lead> getLeads() {
        return leads;
    }

    public void setLeads(Map<Integer, Lead> leads) {
        this.leads = leads;
    }

    public Map<Integer, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<Integer, Contact> contacts) {
        this.contacts = contacts;
    }

    public Map<Integer, Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Map<Integer, Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<Integer, Account> accounts) {
        this.accounts = accounts;
    }
}
