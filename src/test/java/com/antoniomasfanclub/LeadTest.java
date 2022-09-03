package com.antoniomasfanclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LeadTest {

    Lead lead = new Lead();

    @BeforeEach
    void setUp() {
        lead = new Lead("Benito Pérez", "636227551", "beni@email.com", "MediaMarkt");
    }

    @Test
    public void whenNameIsLessThenThree_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setName("El"));
    }

    @Test
    public void whenNameIsMoreThenThree_shouldNotThrowIllegalArgumentException() {
        String newValue = "Eleven";
        lead.setName(newValue);
        assertEquals(newValue, lead.getName());
    }

    @Test
    public void whenPhoneNumberIsLessThenNine_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setPhoneNumber("640"));
    }

    @Test
    public void whenPhoneNumberIsMoreThenNine_shouldNotThrowIllegalArgumentException() {
        String newValue = "640090909";
        lead.setPhoneNumber(newValue);
        assertEquals(newValue, lead.getPhoneNumber());
    }

    @Test
    public void whenEmailIsNotValid_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setEmail("jo"));
    }

    @Test
    public void whenEmailIsValid_shouldNotThrowIllegalArgumentException() {
        String newValue = "jo@jo.com";
        lead.setEmail(newValue);
        assertEquals(newValue, lead.getEmail());
    }


}
