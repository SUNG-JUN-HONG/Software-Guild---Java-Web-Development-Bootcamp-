/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.service;

import com.sg.m3vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.m3vendingmachine.dto.Item;
import com.sg.m3vendingmachine.dto.CurrentBalance;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Sung Jun Hong
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws 
            VendingMachinePersistenceException;
     
    Item getItem(String itemNumber) throws
            VendingMachinePersistenceException;
      
    CurrentBalance purchaseItem(String itemNumber, BigDecimal deposit) throws
            VendingMachinePersistenceException,
            InsufficientFundsException, 
            NoItemInventoryException;
       
    
}