/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import com.masary.database.manager.MasaryManager;

/**
 *
 * @author Hammad
 */
public class waitedTXN implements Runnable {

    String custID = "";
    String amount = "";
    String mobile = "";

    public waitedTXN(String custID, String amount, String mobile) {
        this.custID = custID;
        this.mobile = mobile;
        this.amount = amount;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(605000);
//            Thread.sleep(60000);
            
            String result = MasaryManager.getInstance().transferMobinilTopUpAsService(custID, mobile, amount,"","");
            MasaryManager.logger.info("The waited TXN for Baladna " + result);
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
        }
    }

    
}
