/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.basics.m3vendingmachine;

import com.sg.m3vendingmachine.app.controller.VendingMachineController;
import com.sg.m3vendingmachine.dao.VendingMachineAuditDao;
import com.sg.m3vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.m3vendingmachine.dao.VendingMachineDao;
import com.sg.m3vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.m3vendingmachine.dto.Change;
import com.sg.m3vendingmachine.service.VendingMachineServiceLayer;
import com.sg.m3vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.m3vendingmachine.ui.UserIO;
import com.sg.m3vendingmachine.ui.UserIOConsoleImpl;
import com.sg.m3vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Sung Jun Hong
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        Change myChange = new Change();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao, myChange);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
  
      
    }
}