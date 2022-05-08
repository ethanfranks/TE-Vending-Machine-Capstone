package com.techelevator.view;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountingTest {

    private Accounting accounting;

    @Before
    public void setup(){
        this.accounting = new Accounting();
    }

    // WHY DOESN'T THIS WORK? We're feeding $1.50 into this instance of accounting,
    // then calling change return and providing the expectedResult in the same format as
    // changeReturn() provides.
    @Test
    public void makeChange_balance_is_1dollar_50cent_return_1_dollar_2_quarters() {

        this.accounting.addMoney(new BigDecimal("150"));

        String expectedResult = "Your change is: 1 dollar(s), 2 quarter(s), 0 dime(s), and 0 nickle(s).";
        String actualResult = this.accounting.changeReturn();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void changeReturn_balance_is_10dollar_15cent_return_10_dollars_1_dime_1nickel() {

        this.accounting.addMoney(new BigDecimal("1015"));

        String expectedResult = "Your change is: 10 dollar(s), 0 quarter(s), 1 dime(s), and 1 nickle(s).";
        String actualResult = this.accounting.changeReturn();

        assertEquals(expectedResult, actualResult);

    }

}
