/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 *
 * @author Nourhan
 */
public class CreateAccountHttpURLConnection {

    public String CreateAccount(String request, String Token) {
        HttpURLConnection connection = null;

        try {
            //Create connection

            URL url = new URL(SystemSettingsUtil.getInstance().loadProperty("Create.Account.url"));

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("token", Token);
            connection.setRequestProperty("deviceType", "Web");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            MasaryManager.logger.info("Add to New System  " + request);
            MasaryManager.logger.info("\nSending 'POST' request to URL : " + url);
            MasaryManager.logger.info("Response Code : " + responseCode);
            MasaryManager.logger.info("Response Message : " + connection.getResponseMessage());

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if (responseCode != 201) {
                //print result
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;

                builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(response.toString())));
                Element rootElement = document.getDocumentElement();
                //System.out.println("document  " + document.getElementsByTagName("div").item(2).getTextContent());
                MasaryManager.logger.info("Exception" + document.getElementsByTagName("div").item(2).getTextContent());

            }
            return response.toString();
        } catch (Exception e) {
            MasaryManager.logger.info("Exception" + e.getMessage());
            //System.out.println("Exception" + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}