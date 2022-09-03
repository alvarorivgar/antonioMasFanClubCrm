package com.antoniomasfanclub;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {

    Contact contact1;
    Contact contact2;

    Opportunity opportunity1;
    Opportunity opportunity2;



    @BeforeEach
    void setUp() {
        contact1 = new Contact(new Lead("Esteban Coestáocupado", "687493822", "esteban@email.com", "BBVA"));
        contact2 = new Contact(new Lead("Federico Trillo", "675392876", "fede@email.com", "Construcciones Trillo S.L."));
        opportunity1 = new Opportunity(3, Product.FLATBED, Status.OPEN, contact1);
        opportunity2 = new Opportunity(5, Product.HYBRID, Status.CLOSED_WON, contact2);
    }

   @DisplayName("Test Set Status")
   @Test
    void test_set_status(){
        opportunity1.setStatus(Status.CLOSED_WON);
        assertEquals(Status.CLOSED_WON, opportunity1.getStatus());
   }
   @DisplayName("Test Status NULL")
   @Test
    void test_set_status_NULL(){
       Assertions.assertThrows(IllegalArgumentException.class, () ->opportunity1.setStatus(null));
   }

    @DisplayName("Test opportunity quantity > 1")
    @Test
    void test_opportunity_quantity_bigger_than_1(){
        int newValue = 1;
        opportunity1.setQuantity(newValue);
        assertEquals(newValue, opportunity1.getQuantity());
    }

    @DisplayName("Test opportunity quantity > 1")
    @Test
    void test_opportunity_quantity_throws_when_less_than_1(){
        assertThrows( IllegalArgumentException.class, ()->opportunity1.setQuantity(-1));
    }

    @DisplayName("Contact should be updated easily")
    @Test
    void test_opportunity_set_contact(){
        opportunity1.setContact(contact2);
        assertEquals(contact2, opportunity1.getContact());
    }

}
