/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.services.providers;

import com.google.gson.Gson;
import com.masary.database.dto.EtisalatClubResponse;
import com.masary.utils.SystemSettingsUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;
import org.apache.log4j.Logger;

/**
 *
 * @author Kerolous
 */
public class EtisalatClubClient {

    private final Logger logger = Logger.getLogger(EtisalatClubClient.class.getName());

    public EtisalatClubResponse getClubInfo(String msisdn) throws IOException {
        Gson gson = new Gson();
        EtisalatClubResponse etisalatClubResponse = null;
        try {
            // get the property value and print it out
            String ip = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.ip");
            String port = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.port");
            String username = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.username");
            String userpass = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.userpass");

            //Build etisalat request
            String request = "operation_id=2222<<>>TYPE_ID=5" + "<<>>MSISDN=" + msisdn
                    + "<<>>username=" + username + "<<>>userPass=" + userpass;

            String result = CallEtisalatService(request, ip, Integer.parseInt(port));
            //System.out.println("Result" + result);
            if (!result.equals("{}")) {
                etisalatClubResponse = gson.fromJson(result, EtisalatClubResponse.class);
                etisalatClubResponse.setMessage(new String(etisalatClubResponse.getMessage().getBytes(), Charset.forName("UTF-8")));
            } else {
                etisalatClubResponse = null;
            }

            return etisalatClubResponse;
        } catch (Exception e) {
            logger.error("error in getting property value " + e);
            return new EtisalatClubResponse(null, null, null, null);
        } finally {
        }

    }

    public String CallEtisalatService(String request, String ip, int port) {
        Socket clientSocket = null;
        BufferedReader inFromServer = null;
        DataOutputStream outToServer = null;
        try {
            String modifiedSentence;
            logger.info("Request : " + request);
            clientSocket = new Socket(ip, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(request + '\n');
            outToServer.flush();
            modifiedSentence = inFromServer.readLine();

            return modifiedSentence;
        } catch (IOException ex) {
            logger.error("error in calling etisalat club service " + ex);
            return new Gson().toJson(new EtisalatClubResponse(null, null, null, null));
        } finally {
            try {
                inFromServer.close();
                outToServer.close();
                clientSocket.close();
            } catch (IOException ex) {
                logger.error("error while closing resources " + ex);
            }
        }

    }

}
