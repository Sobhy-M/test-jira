/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;

import com.google.gson.Gson;
import com.masary.database.dto.BlaBlaProducts_Res;
import com.masary.database.dto.Product;
import com.masary.database.manager.MasaryManager;


/**
 *
 * @author Aya
 */
public class BlaBla_Manager {

    public static String username = "emasary";
    public static String password = "masaryblabla2015";
    public static String urlString = "http://distributors.blablacash.com/";

    public static void main(String[] args) {
//       getTopupCountries();
        // Generate nonce
        String nonce = getNonce();
        //System.out.println(nonce);
        // Generate hashed password
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");

            String hashedPassword = new String(byteArrayToHexString(messageDigest.digest(password.getBytes())));
            //System.out.println(hashedPassword);
            hashedPassword = new String(byteArrayToHexString(messageDigest.digest((nonce + ":" + hashedPassword).getBytes())));
            //System.out.println(username);
            //System.out.println(password);
            // Prepare request
//            String urlParameters = "username=" + username + "&password=" + hashedPassword + "&nonce=" + nonce+"&product_id=1&beneficiary=01224346692";
            String urlParameters = "username=" + username + "&password=" + hashedPassword + "&nonce=" + nonce;
            String url = "http://distributors.blablacash.com/webservices/getBlaBlaProducts";
//            String url = "https://distributors.blablacash.com/webservices/blaBlaTopup";
            String Response = SendRequest(url, urlParameters);
            MasaryManager.logger.error(Response);

            Gson gson = new Gson();
            BlaBlaProducts_Res BlaBlaProducts_res = gson.fromJson(Response, BlaBlaProducts_Res.class);
//            //System.out.println(BlaBlaProducts_res.getStatus());
//            MasaryManager.logger.error(ex.getMessage());

            for (Product Product : BlaBlaProducts_res.getValue()) {
//                //System.out.println(Product.getId());
                MasaryManager.logger.error(Product.getId());

            }
        } catch (Exception ex) {
//            //System.out.println("Exception"+ex.getMessage());
            MasaryManager.logger.error(ex.getMessage());

        }
    }


    public static String getNonce() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public static String SendRequest(String urll, String urlParameters) {
        String response = "";
        // Prepare request
        URL url;
        try {
            url = new URL(urll);
            SSLUtilities.trustAllHttpsCertificates();
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setDoInput(true);
            httpCon.setInstanceFollowRedirects(false);
            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestProperty("charset", "utf-8");
            httpCon.setRequestProperty("Content-Length", "" + urlParameters.getBytes().length);
            httpCon.setUseCaches(false);
//            //System.out.println(urll);
//            //System.out.println(urlParameters);
            // Send request with parameters
            DataOutputStream wr = new DataOutputStream(httpCon.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            // Get the Content from the response
            InputStreamReader in = new InputStreamReader((InputStream) httpCon.getContent());
            BufferedReader buffer = new BufferedReader(in);
            response = buffer.readLine();
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
//            //System.out.println("Exception"+ex.getMessage());
        }
        return response;
    }
}
