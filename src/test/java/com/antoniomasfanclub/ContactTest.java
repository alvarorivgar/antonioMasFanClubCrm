package com.antoniomasfanclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContactTest {

    Lead lead = new Lead();
    Contact contact = new Contact(lead);


    @Test
    public void whenNameIsLessThenThree_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setName("Ju"));
    }

    @Test
    public void whenNameIsMoreThenThree_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> contact.setName("Julia"));
    }

    @Test
    public void whenPhoneNumberIsLessThenNine_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setPhoneNumber("640"));
    }

    @Test
    public void whenPhoneNumberIsMoreThenNine_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> contact.setPhoneNumber("640090909"));
    }

    @Test
    public void whenEmailIsNotValid_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> contact.setEmail("jo"));
    }

    @Test
    public void whenEmailIsValid_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> contact.setEmail("jo@jo.com"));
    }

}