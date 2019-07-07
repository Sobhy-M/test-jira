/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.agentPayment;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import com.oschrenk.io.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 *
 * @author y
 */
public class AgentInquiry {

    private String responseMessage;
    private int responseCode;
    private AgentClientInquiryResponse agentResponse;

    public void run(String agentId, String agentRip, String userName) {
        URL url;
        HttpURLConnection connection;
        Gson gson = new Gson();
        try {
            String urlString = SystemSettingsUtil.getInstance().loadProperty("agent.payment.inquiry.url");
            url = new URL(urlString + agentId + "/" + agentRip);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String authorization = generateSecurityToken(userName);
            connection.setRequestProperty("Authorization", authorization);

            responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = getResponseBody(new InputStreamReader(connection.getInputStream()));
                agentResponse = gson.fromJson(response.toString(), AgentClientInquiryResponse.class);
                MasaryManager.logger.info("Agent Inquiry response with : " + response.toString());
            } else {
                String response = getResponseBody(new InputStreamReader(connection.getErrorStream()));
                MasaryManager.logger.error(response.toString());
                if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                    responseMessage = "لا يوجد مندوب بهذا الكود";
                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    responseMessage = "هذا المندوب غير مضاف لهذا الوكيل";
                } else {
                    responseMessage = "خطأ فى العملية";
                }
            }
        } catch (MalformedURLException ex) {
            responseMessage = "خطأ فى العملية";
            MasaryManager.logger.error("Error in AgentClientInquiryResponse Class : MalformedURLException  " + ex);
        } catch (Exception e) {
            responseMessage = "خطأ فى العملية";
            MasaryManager.logger.error("Error in AgentClientInquiryResponse Class : IOException  " + e);
        }
    }

    private String generateSecurityToken(String userName) {
        try {
            String encodedKey = "zBlqdzSLBQLu3KiAdrM53Q==";
            int expiryTimeInMinutes = 1;
            StringBuilder rolesBuilder = new StringBuilder("all roles");

            byte[] decodedKey = Base64.decode(encodedKey);

            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, expiryTimeInMinutes);
            Date date = c.getTime();

            String s = Jwts.builder().setSubject(userName).claim("roles", rolesBuilder.toString()).setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, originalKey).compact();

            return "Bearer " + s;
        } catch (Exception ex) {
            MasaryManager.logger.info("Exc :", ex);
        }
        catch (Error e) {
            MasaryManager.logger.info("Exc :", e);
        }
        return "Bearer ";
    }

    private String getResponseBody(InputStreamReader reader) {
        BufferedReader in = null;
        StringBuilder response = new StringBuilder();
        try {
            in = new BufferedReader(reader);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException ex) {
            MasaryManager.logger.info(ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                MasaryManager.logger.info(ex);
            }
        }
        return response.toString();
    }

    public String getAgentName() {
        if (agentResponse == null) {
            return "";
        }
        return agentResponse.getAgentName();
    }

    public String getRepName() {
        if (agentResponse == null) {
            return "";
        }
        return agentResponse.getRepName();
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    private class AgentClientInquiryResponse {

        private String agentName;
        private String repName;

        public AgentClientInquiryResponse() {
        }

        public AgentClientInquiryResponse(String agName, String repName) {
            this.agentName = agName;
            this.repName = repName;
        }

        public String getAgentName() {
            return agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public String getRepName() {
            return repName;
        }

        public void setRepName(String repName) {
            this.repName = repName;
        }

        @Override
        public String toString() {
            return "InquiryResponse [agentName=" + agentName + ", repName=" + repName + "]";
        }
    }
}
