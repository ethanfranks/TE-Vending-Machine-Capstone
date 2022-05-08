package com.techelevator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class InventoryMapTest {

    private InventoryMap inventoryMap;

    @Before
    public void setup() {
        this.inventoryMap = new InventoryMap();
    }

    @Test
    public void initial_inventory_stock_set_to_7() {
        int expectedValue = 7;
        inventoryMap.restockInventory();
        int actualValue = inventoryMap.getInventory().get("A4").getQuantity();
        assertEquals(expectedValue, actualValue);

        int expectedValue2 = 7;
        inventoryMap.restockInventory();
        int actualValue2 = inventoryMap.getInventory().get("B3").getQuantity();
        assertEquals(expectedValue2, actualValue2);
    }

}
