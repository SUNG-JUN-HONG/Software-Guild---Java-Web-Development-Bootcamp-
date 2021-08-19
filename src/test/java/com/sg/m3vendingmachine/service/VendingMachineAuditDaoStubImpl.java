/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m3vendingmachine.service;

import com.sg.m3vendingmachine.dao.VendingMachineAuditDao;
import com.sg.m3vendingmachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author Sung Jun Hong
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
      //do nothing...
    }
}
