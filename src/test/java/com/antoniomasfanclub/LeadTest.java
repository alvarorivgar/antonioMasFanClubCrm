package com.antoniomasfanclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LeadTest {

    Lead lead = new Lead();

    @Test
    public void whenNameIsLessThenThree_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setName("El"));
    }

    @Test
    public void whenNameIsMoreThenThree_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> lead.setName("Eleven"));
    }

    @Test
    public void whenPhoneNumberIsLessThenNine_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setPhoneNumber("640"));
    }

    @Test
    public void whenPhoneNumberIsMoreThenNine_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> lead.setPhoneNumber("640090909"));
    }

    @Test
    public void whenEmailIsNotValid_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> lead.setEmail("jo"));
    }

    @Test
    public void whenEmailIsValid_shouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> lead.setEmail("jo@jo.com"));
    }


}