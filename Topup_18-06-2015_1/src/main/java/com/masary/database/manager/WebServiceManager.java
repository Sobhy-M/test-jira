/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.AuthenticationResponse;

/**
 *
 * @author Melad
 */
public class WebServiceManager {
    public AgentDTO getBalance(String userId, String password) throws Exception {
        if (!authenticate(userId, password)) {
            return null;
        }
        return MasaryManager.getInstance().getAgent(userId);
    }

    synchronized boolean authenticate(String userId, String password) throws Exception {
        AuthenticationResponse authenticationResponse = MasaryManager.getInstance().authenticateSiteUserID(userId, password);
        if (authenticationResponse.getRole().equals("-1")) {
            throw new Exception("-2 Bad user ID or password");
        } else if (authenticationResponse.getRole().equals("-2")) {
            throw new Exception("-3 Your account currently not active Conntact Massary Admin for more information");
        } else if (authenticationResponse.getRole().equals("-4")) {
            throw new Exception("-100 General Error Contact Masary Admin ");
        }
        return true;
    }

    public String topupEtisalt(String userId, String password, int amount, String msisdn) throws Exception {
        if (!authenticate(userId, password)) {
            return null;
        }
        if (msisdn == null) {
            throw new Exception("-1 Bad request");
        }
        if (amount <= 2) {
            throw new Exception("-1 Amount must be grater than 0.");
        }
        double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userId, 6);
        if (serviceBalance < amount) {
            throw new Exception("-4 You don't have enough balance.");
        }
        if (msisdn.startsWith("0")) {
            msisdn = msisdn.substring(1);
        }
        return MasaryManager.getInstance().transferCustomerTopUpAsService(userId, msisdn, amount + "", "", "");
    }
}
