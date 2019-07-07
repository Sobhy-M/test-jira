/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hammad
 */
public class TopupServiceIntegrationStatus implements Runnable {

    public static volatile boolean orangeserviceStatus;
    public static volatile boolean etisalatserviceStatus;
    public static volatile boolean vodafoneserviceStatus;
    public static volatile boolean  moneyTransferServiceStatus;

    //8 is  orange topup id
//    private static int topupType = 8;
    @Override

    public void run() {

        Integer OrangeTopupServiceId = null;
        Integer etisalatTopupServiceId = null;
        Integer vodafoneTopupServiceId = null;
        Integer moneyTransferServiceId = null;
        try {
            OrangeTopupServiceId = Integer.parseInt(SystemSettingsUtil.getInstance().loadProperty("orangeTopup.serviceId"));
            etisalatTopupServiceId = Integer.parseInt(SystemSettingsUtil.getInstance().loadProperty("etisalatTopup.serviceId"));
            vodafoneTopupServiceId = Integer.parseInt(SystemSettingsUtil.getInstance().loadProperty("vodafoneTopup.serviceId"));
//            moneyTransferServiceId = Integer.parseInt(SystemSettingsUtil.getInstance().loadProperty("moneyTransfer.serviceId"));
            
            MasaryManager.logger.info("Topup service Id is " + OrangeTopupServiceId);
            MasaryManager.logger.info("Topup service Id is " + etisalatTopupServiceId);
            MasaryManager.logger.info("Topup service Id is " + vodafoneTopupServiceId);
//            MasaryManager.logger.info("Topup service Id is " + moneyTransferServiceId);
        } catch (NumberFormatException ex) {
            Logger.getLogger(TopupServiceIntegrationStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            orangeserviceStatus = MasaryManager.getInstance().getServiceStatus(OrangeTopupServiceId);
            etisalatserviceStatus = MasaryManager.getInstance().getServiceStatus(etisalatTopupServiceId);
            vodafoneserviceStatus = MasaryManager.getInstance().getServiceStatus(vodafoneTopupServiceId);
//            moneyTransferServiceStatus = MasaryManager.getInstance().getServiceStatus(moneyTransferServiceId);
            MasaryManager.logger.info("topup service integration 8: " + orangeserviceStatus);
            MasaryManager.logger.info("topup service integration 6: " + etisalatserviceStatus);
            MasaryManager.logger.info("topup service integration 10: " + vodafoneserviceStatus);
//            MasaryManager.logger.info("topup service integration 10: " + moneyTransferServiceId);
        	
        } catch(Exception ex){
        	MasaryManager.logger.error(ex.getMessage() , ex);
        }
   
    }
}
