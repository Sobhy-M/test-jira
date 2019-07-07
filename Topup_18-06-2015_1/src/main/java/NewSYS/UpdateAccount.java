/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewSYS;

import com.masary.database.manager.MasaryManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 *
 * @author Nourhan
 */
public class UpdateAccount {

    public static String UpdateAccount(String accountid,String accountDTO) {
        HttpURLConnection connection = null;

        String urlParameters = accountDTO;
//        accountid="24551";
        try {
            //Create connection
//            URL url = new URL("http://196.218.51.74:9005/account/" + accountid);
            URL url = new URL("http://10.29.10.234:9005/account/" + accountid);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
             connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String userCredentials = "shady:ragab";
            String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userCredentials.getBytes("UTF-8"));
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestProperty("deviceType", "Web");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);


            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            MasaryManager.logger.info("Update to New System  " + urlParameters);
            MasaryManager.logger.info("\nSending 'POST' request to URL : " + url);
            MasaryManager.logger.info("Response Code : " + responseCode);
            MasaryManager.logger.info("Response Message : " + connection.getResponseMessage());
            //System.out.println("\nSending 'PUT' request to URL : " + url);
            //System.out.println("PUT parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

       if(responseCode!=200){
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
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

}
