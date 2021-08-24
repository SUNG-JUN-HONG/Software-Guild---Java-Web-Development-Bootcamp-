/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.ui;

import com.sg.m3vendingmachine.dto.Change;
import com.sg.m3vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Sung Jun Hong
 */
public class VendingMachineView {

   private UserIO io;
   private Change change;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("\n");
        io.print("M3 Vending Machine");
        io.print("1. Purchase the drink");
        io.print("2. List the drink informaton");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemNumber() + ": "
                    + currentItem.getItemName() + " "
                    + currentItem.getItemPrice() + " "
                    + currentItem.getItemQuantity());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayItem(Item item) {
	    if (item != null) {
	        io.print(item.getItemNumber());
	        io.print(item.getItemName());
	        io.printBigDecimal(item.getItemPrice());
                io.printInt(item.getItemQuantity());
	        io.print("");
	    } else {
	        io.print("No such Item.");
	    }
	    io.readString("Please hit enter to continue.");
	}

    public void displayItemBanner() {
        io.print("=== Item ===");
    }
    
    public void displayItemPurchaseBanner() {
        io.print("=== Purchase Item ===");
    }
    
    public String getItemNumberChoice() {
        return io.readString("Please enter the Drink Number.");
    }

    public String displayThankYouPurchase() {
        return io.readString("Thank You for your purchase! (Enter to continue)");
    }

    public void displayDisplayAllBanner() {
        io.print("=== All Drinks ===");
    }

    public void displayExitBanner() {
        io.print("Thank you. Have a great day.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Please enter the correct command. This is unknown");
    }
    
    public BigDecimal displayRequestDeposit(){
        String cash = io.readString("Please deposit moeny");
        BigDecimal bd = new BigDecimal(cash);
        
        return bd; 
    }
    
    public void displayDepositSuccessful(){
        io.print("Deposit is done");
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}
   
    public void displayChange(String change) {
        io.print("Your current change : " + change);
    }
    
    public void displyChangeInDouble(double balance){
        io.print("The remaining deposit is :$"+balance);
    }

}	