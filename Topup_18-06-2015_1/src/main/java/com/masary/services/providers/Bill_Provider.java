/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.services.providers;

import com.masary.database.dto.Main_Provider;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Error;
import com.masary.database.dto.Masary_Payment_Request;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.manager.MasaryManager;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael
 */
public class Bill_Provider {

    public Masary_Bill_Response billInquiry(Main_Provider provider, String BTC, String phone, String custId, HttpSession session) {
        Masary_Bill_Response response = new Masary_Bill_Response();
        int provider_id = provider.getPROVIDER_ID();
        switch (provider_id) {
            case 3:
                MasaryManager.getInstance().logger.info("[Do bill inquiry with Etisalat]");
                Etisalat etisalat = new Etisalat();
                response = etisalat.DO_Bill_Inquiry(provider, BTC, phone, custId,session);
                break;
            case 4:
                MasaryManager.getInstance().logger.info("[Do bill inquiry with e-finance]");
                efinance efinance = new efinance();
                response = efinance.DO_Bill_Inquiry(provider, BTC, phone, custId, session);
                break;
        }
        return response;
    }

    public Masary_Error sendpaymentInquiry(String custId, String employeeID, Masary_Bill_Response bill_Response, double amount, Main_Provider provider, HttpSession session, String isValidDTO) {
        Masary_Error response = new Masary_Error();
        int provider_id = provider.getPROVIDER_ID();
        switch (provider_id) {
            case 3:
                MasaryManager.getInstance().logger.info("[Do bill Payment with Etisalat]");
                Etisalat etisalat = new Etisalat();
                response = etisalat.sendpaymentInquiry(custId, employeeID, bill_Response, amount, provider, session, isValidDTO);
                break;
            case 4:
                MasaryManager.getInstance().logger.info("[Do bill Payment with e-finance]");
                efinance efinance = new efinance();
                response = efinance.sendpaymentInquiry(custId, employeeID, bill_Response, amount, provider, session, isValidDTO);
                break;
        }
        return response;
    }

    public double getBalance(Main_Provider provider, String custId) {
        int provider_id = provider.getPROVIDER_ID();
        double balance = 0;
        switch (provider_id) {
            case 3:
                MasaryManager.getInstance().logger.info("[Do balance inquiry with Etisalat]");
                Etisalat etisalat = new Etisalat();
                balance = etisalat.getBalance(provider, custId);
                break;
            case 4:
                MasaryManager.getInstance().logger.info("[Do balance inquiry with e-finance]");
                efinance efinance = new efinance();
                balance = efinance.getBalance(provider, custId);
                break;
        }
        return balance;
    }
}
