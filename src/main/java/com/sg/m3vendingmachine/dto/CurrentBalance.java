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
public class CurrentBalance {
    String remainingDepositMessage;
    double currentBalance;
    
    public CurrentBalance(String message, double balance){
        this.remainingDepositMessage = message;
        this.currentBalance = balance;
    }

    public String getRemainingDepositMessage() {
        return remainingDepositMessage;
    }

    public void setRemainingDepositMessage(String remainingDepositMessage) {
        this.remainingDepositMessage = remainingDepositMessage;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    
    
}
