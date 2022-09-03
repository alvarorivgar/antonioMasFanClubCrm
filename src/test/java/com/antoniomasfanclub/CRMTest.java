package com.antoniomasfanclub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CRMTest {

    private CRM crm;
    private Lead lead1;
    private Contact contact1;
    private  Opportunity opportunity1;
    private Account account1;

    @BeforeEach
    void setUp(){

        crm = new CRM();
        lead1 = new Lead("Perico Palotes", "658987412", "perico@palotes.com", "Palotes Perico");
        contact1 = new Contact(new Lead("Esteban Coestáocupado", "687493822", "esteban@email.com", "BBVA"));
        opportunity1 = new Opportunity(3, Product.FLATBED, Status.OPEN, contact1);
        account1 = new Account(Industry.MANUFACTURING, 135, "Barcelona", "Spain");
        //this.crm.addAccount(new Account(Industry.ECOMMERCE, 56, "Madrid", "Spain"));
    }
    @Test
    void addLead() {
        crm.addLead(lead1);
        assertEquals("Perico Palotes", crm.getLead(lead1.getId()).getName());
        assertEquals(1, crm.leads.size());
    }

    @Test
    void deleteLead() {
        crm.addLead(lead1);
        crm.deleteLead(lead1.getId());
        assertTrue(crm.leads.isEmpty());
    }

    @Test
    void getLead() {
        assertThrows(IllegalArgumentException.class, ()-> crm.getLead(4));
    }

    @Test
    void addContact() {
        crm.addContact(contact1);
        assertEquals("Esteban Coestáocupado", contact1.getName());
        assertEquals(1, crm.contacts.size());
    }

    @Test
    void deleteContact() {
        crm.addContact(contact1);
        crm.deleteContact(contact1.getId());
        assertTrue(crm.contacts.isEmpty());
    }

    @Test
    void getContact() {
        assertThrows(IllegalArgumentException.class, ()-> crm.getContact(4));
    }

    @Test
    void addOpportunity() {
        crm.addOpportunity(opportunity1);
        assertEquals(3, opportunity1.getQuantity());
        assertEquals(1, crm.opportunities.size());
    }

    @Test
    void deleteOpportunity() {
        crm.addOpportunity(opportunity1);
        crm.deleteOpportunity(opportunity1.getId());
        assertTrue(crm.opportunities.isEmpty());
    }

    @Test
    void getOpportunity() {
        assertThrows(IllegalArgumentException.class, ()-> crm.getOpportunity(4));
    }

    @Test
    void addAccount() {
        crm.addAccount(account1);
        assertEquals(135, account1.getEmployeeCount());
        assertEquals(1, crm.accounts.size());
    }

    @Test
    void deleteAccount() {
        crm.addAccount(account1);
        crm.deleteAccount(account1.getId());
        assertTrue(crm.accounts.isEmpty());
    }

    @Test
    void getAccount() {
        assertThrows(IllegalArgumentException.class, ()-> crm.getAccount(4));
    }
}