/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.service;

import com.sg.m3vendingmachine.dao.VendingMachineAuditDao;
import com.sg.m3vendingmachine.dao.VendingMachineDao;
import com.sg.m3vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.m3vendingmachine.dto.Change;
import com.sg.m3vendingmachine.dto.Item;
import com.sg.m3vendingmachine.dto.CurrentBalance;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Sung Jun Hong
 */
public class VendingMachineServiceLayerImpl implements
       
     VendingMachineServiceLayer{
 
    VendingMachineAuditDao auditDao;
    VendingMachineDao dao;
    Change change;
    

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao, Change change) {
       this.dao = dao;
       this.auditDao = auditDao;
       this.change = change;
    }

    @Override
    public List<Item> getAllItems() throws
            VendingMachinePersistenceException {
       return dao.getAllItems();
    }
    
     @Override
    public Item getItem(String itemNumber) throws
            VendingMachinePersistenceException {
        return dao.getItemPurchased(itemNumber);
    }
    
    @Override
    public CurrentBalance purchaseItem(String itemNumber, BigDecimal deposit) throws
           VendingMachinePersistenceException, 
           InsufficientFundsException, 
           NoItemInventoryException {
       
        //gets requested item
        Item item = dao.getItemPurchased(itemNumber);
        
        //checks if inventory is less than 0
        if(item.getItemQuantity() <= 0) {
            throw new NoItemInventoryException(
                "Drink: "
                + item.getItemName()
                + " is not in stock!");
        }
        
        //check if deposit is greater than or equal to price
         if(deposit.compareTo(item.getItemPrice()) < 0) {
             throw new InsufficientFundsException("Insuffient Deposit");
         }
        
        //pass in price and deposit into change class and calculate change
        double myChangeInDouble = change.makeChange(item, deposit);
        
        //Update the item information by subtracting 1 from item object(inventory)
        dao.purchaseItem(itemNumber);
        
        auditDao.writeAuditEntry("The item : " + item.getItemName()+ " Purchased");
        
        String stringChange = "\n " + change.getDollars()+ " Dollars "
                            +"\n " + change.getQuarters()+ " Quarters "
                            +"\n " + change.getDimes()+ " Dimes "
                            +"\n " + change.getNickels()+ " Nickels "
                            +"\n " + change.getPennies()+ " Pennies";

        CurrentBalance cb = new CurrentBalance(stringChange, myChangeInDouble);
        
        //return stringChange;
        return cb;
        
    }

    

}