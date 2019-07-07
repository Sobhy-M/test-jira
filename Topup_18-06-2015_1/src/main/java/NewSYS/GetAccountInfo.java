/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewSYS;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Nourhan
 */
public class GetAccountInfo {

//    public static void main(String[] args) {
//        GetAccount("24551");
//    }

    public static getinfoDTO GetAccount(String Account_Id) {
        HttpURLConnection connection = null;
        Gson gson = new Gson();
        getinfoDTO getinfo = null;
        try {
            //Get Account Info connection
//            URL url = new URL("http://196.218.51.74:9005/account/name/" + Account_Id.trim() + "/full");
            URL url = new URL("http://10.29.10.234:9005/account/name/" + Account_Id.trim() + "/full");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
//            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.flush();
//            wr.close();
            int responseCode = connection.getResponseCode();
            MasaryManager.logger.info("\nSending 'GEt' request to URL : " + url);
            MasaryManager.logger.info("Response Code : " + responseCode);
            MasaryManager.logger.info("Response Message : " + connection.getResponseMessage());
           // //System.out.println("\nSending 'POST' request to URL : " + url);
//            //System.out.println("Post parameters : " + urlParameters);
            ////System.out.println("Response Code : " + responseCode);
           // //System.out.println("Response Message : " + connection.getResponseMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

                getinfo = gson.fromJson(response.toString(), getinfoDTO.class);
                ////System.out.println("Response Code : " + getinfo.getAccountId());
            }
            in.close();
            //print result
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder;
//
//            builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new InputSource(new StringReader(response.toString())));
//            Element rootElement = document.getDocumentElement();
//            //System.out.println("document  " + document.getElementsByTagName("div").item(2).getTextContent());
            return getinfo;

        } catch (Exception e) {
            ////System.out.println("Exception" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

}
