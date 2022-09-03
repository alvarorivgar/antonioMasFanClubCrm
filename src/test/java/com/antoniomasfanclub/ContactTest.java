package com.antoniomasfanclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContactTest {

    Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact(new Lead("Benito Pérez", "636227551", "beni@email.com", "MediaMarkt"));
    }

    @Test
    public void whenNameIsLessThenThree_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setName("El"));
    }

    @Test
    public void whenNameIsMoreThenThree_shouldNotThrowIllegalArgumentException() {
        String newValue = "Eleven";
        contact.setName(newValue);
        assertEquals(newValue, contact.getName());
    }

    @Test
    public void whenPhoneNumberIsLessThenNine_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setPhoneNumber("640"));
    }

    @Test
    public void whenPhoneNumberIsMoreThenNine_shouldNotThrowIllegalArgumentException() {
        String newValue = "640090909";
        contact.setPhoneNumber(newValue);
        assertEquals(newValue, contact.getPhoneNumber());
    }

    @Test
    public void whenEmailIsNotValid_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setEmail("jo"));
    }

    @Test
    public void whenEmailIsValid_shouldNotThrowIllegalArgumentException() {
        String newValue = "jo@jo.com";
        contact.setEmail(newValue);
        assertEquals(newValue, contact.getEmail());
    }

}
