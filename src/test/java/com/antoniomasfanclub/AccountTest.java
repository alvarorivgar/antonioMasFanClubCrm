package com.antoniomasfanclub;

import org.junit.jupiter.api.Assertions;
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

    @DisplayName("Test emplyeeAcount > 1")
    @Test
    void test_employeeAcount_bigger_than_1(){
        assertTrue(account1.getEmployeeCount() >1);
    }


}