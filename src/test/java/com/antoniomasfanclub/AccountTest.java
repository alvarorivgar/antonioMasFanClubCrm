package com.antoniomasfanclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account1;
    Account account2;

    @BeforeEach
    void setUp() {
        account1 = new Account(Industry.MANUFACTURING, 135, "Barcelona", "Spain");
        account2 = new Account(Industry.ECOMMERCE, 56, "Madrid", "Spain");
    }

    @DisplayName("Test Set Industry")
    @Test
    void test_set_industry(){
        account1.setIndustry(Industry.MEDICAL);
        assertEquals(Industry.MEDICAL, account1.getIndustry());
    }

    @DisplayName("Test employeeAccount > 1")
    @Test
    void test_employeeAccount_bigger_than_1(){
        int newValue = 1;
        account1.setEmployeeCount(newValue);
        assertEquals(newValue, account1.getEmployeeCount());
    }

    @DisplayName("Test employeeAccount > 1")
    @Test
    void test_employeeAccount_throws_when_less_than_1(){
        assertThrows( IllegalArgumentException.class, ()->account1.setEmployeeCount(-1));
    }
}
