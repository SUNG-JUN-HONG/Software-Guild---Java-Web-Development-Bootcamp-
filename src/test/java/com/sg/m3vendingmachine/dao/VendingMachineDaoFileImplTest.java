/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.dao;

import com.sg.m3vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sung Jun Hong
 */
public class VendingMachineDaoFileImplTest {
   
    VendingMachineDao dao = new VendingMachineDaoFileImpl("TestReciept.txt");
    File file = new File("TestReciept.txt");
    
    Item item = new Item("1");

    
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    
    @BeforeEach
    public void setUp() throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TestReciept.txt"), "utf-8"))) {
            writer.write("1::Coke::1.50::10"
                    + "\n2::Sprite::.75::8"
                    + "\n3::CherryCoke::1.00::6");
        }
        
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
        
    }
    
    @AfterEach
    public void tearDown() {
    }

 @Test
    public void testListAllItems() throws Exception {
        List<Item> allItems = dao.getAllItems();
        assertEquals(3, allItems.size());
    }


    @Test
    public void testGetItem() throws Exception {
        
        assertEquals(item, dao.getItemPurchased("1"));
    }


    @Test
    public void testPurchaseItem() throws Exception {
        dao.purchaseItem("1");
        assertEquals(9, dao.getItemPurchased("1").getItemQuantity());
    }
 
    
}
