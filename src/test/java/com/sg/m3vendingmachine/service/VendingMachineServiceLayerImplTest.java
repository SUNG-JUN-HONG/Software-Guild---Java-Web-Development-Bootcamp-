/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.service;

import com.sg.m3vendingmachine.dao.VendingMachineAuditDao;
import com.sg.m3vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.m3vendingmachine.dao.VendingMachineDao;
import com.sg.m3vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.m3vendingmachine.dto.Change;
import com.sg.m3vendingmachine.dto.CurrentBalance;
import com.sg.m3vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
public class VendingMachineServiceLayerImplTest {
    
   VendingMachineDao dao = new VendingMachineDaoFileImpl("TestReciept.txt");
   
    VendingMachineServiceLayer service; 
    File file = new File("TestReciept.txt");
    Item item = new Item("1");

    public VendingMachineServiceLayerImplTest() {
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        Change change = new Change();
        service = new VendingMachineServiceLayerImpl(dao, auditDao, change);        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TestReciept.txt"), "utf-8"))) {
            writer.write("1::Coke::1.50::10"
                    + "\n2::Sprite::.75::8"
                    + "\n3::CherryCoke::1.00::0");
        }
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);           
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = service.getAllItems();
        assertEquals(3, allItems.size());
    }

    @Test
    public void testGetItem() throws Exception {
          assertEquals(item, service.getItem("1"));
    }

    @Test
    public void testPurchaseItem() throws Exception {
      CurrentBalance purchaseItem = service.purchaseItem("1", new BigDecimal("2.00"));
       assertEquals( "Your recieving 0 Dollars \nYour recieving 2 Quarters \nYour recieving 0 Dimes \nYour recieving 0 Nickels \nYour recieving 0 Pennies", purchaseItem);
    }
    
    @Test 
    public void testPurchaseItemInsufficientFundsExp() throws Exception {
        try{
          service.purchaseItem("1", new BigDecimal(".25"));
        }catch (InsufficientFundsException e) {
        }
    }
    
    @Test
    public void testNoInventoryExp() throws Exception {
       try{
       service.purchaseItem("3", new BigDecimal("5.00"));
       } catch (NoItemInventoryException e){
           
       }
    }    
    
    
}
