/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.app.controller;

import com.sg.m3vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.m3vendingmachine.dto.Item;
import com.sg.m3vendingmachine.service.InsufficientFundsException;
import com.sg.m3vendingmachine.service.NoItemInventoryException;
import com.sg.m3vendingmachine.service.VendingMachineServiceLayer;
import com.sg.m3vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Sung Jun Hong
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            listItems();

            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        purchaseItem();
                        break;
                    case 2:
                        listItems();
                        viewItem();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listItems()
            throws VendingMachinePersistenceException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem()
            throws VendingMachinePersistenceException {
        view.displayItemBanner();
        String itemNumber = view.getItemNumberChoice();
        Item item = service.getItem(itemNumber);
        view.displayItem(item);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void purchaseItem()
            throws VendingMachinePersistenceException {
       
        BigDecimal deposit = requestDeposit();

        boolean hasErrors = false;
     
            String itemNumber = view.getItemNumberChoice();
            try {
                String purchasedItem = service.purchaseItem(itemNumber, deposit);
                view.displayChange(purchasedItem);
                view.displayThankYouPurchase();
                hasErrors = false;
            } catch (InsufficientFundsException | NoItemInventoryException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
    
            
    }

    private BigDecimal requestDeposit() {
        BigDecimal deposit = view.displayRequestDeposit();
        view.displayDepositSuccessful();
        return deposit;
    }

   

}