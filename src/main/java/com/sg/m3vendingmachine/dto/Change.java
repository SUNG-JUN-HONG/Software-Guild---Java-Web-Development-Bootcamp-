/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Sung Jun Hong
 */
public class Change {

    Item item;

    private int dollars, quarters, dimes, nickels, pennies;
    
    
    
    public double makeChange(Item item, BigDecimal deposit) {

        BigDecimal one = new BigDecimal("1");
        BigDecimal five = new BigDecimal("5");
        BigDecimal ten = new BigDecimal("10");
        BigDecimal twentyFive = new BigDecimal("25");
        BigDecimal oneHundread = new BigDecimal("100");
        
        BigDecimal price = item.getItemPrice();
        
     
        BigDecimal myChange = deposit.subtract(price);

        myChange = myChange.multiply(oneHundread);
        
        double myChangeInDouble = (myChange.floatValue())/100;
        
        dollars = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
        
        
        // .floatValue() is for converting a BigDecimal object into a float value.
        while (myChange.floatValue() > 0) {
            { 
                if (myChange.floatValue() >= 100) {
                  myChange =  myChange.subtract(oneHundread);
                    dollars++;
                } else if (myChange.floatValue() >= 25) {
                    myChange =  myChange.subtract(twentyFive);
                    quarters++;
                } else if (myChange.floatValue() >= 10) {
                    myChange =  myChange.subtract(ten);
                    dimes++;
                } else if (myChange.floatValue() >= 5) {
                    myChange =  myChange.subtract(five);
                    nickels++;
                } else if (myChange.floatValue() >= 1) {
                    myChange =  myChange.subtract(one);
                    pennies++;
                }
             
            }
        }
        
        return myChangeInDouble;

    }

    public int getDollars() {
        return dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }



}
