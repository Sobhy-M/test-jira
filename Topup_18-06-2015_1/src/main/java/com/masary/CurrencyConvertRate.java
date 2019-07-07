package com.masary;

import com.masary.database.dto.CurrencyConvertResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aya
 */
public class CurrencyConvertRate {

//    public  String SendRequestandConvert (int snum) 
//    
//    {
//         CurrencyConvertResponse r=new  CurrencyConvertResponse();
//       
//        String res;
//        URL url;
//        try {
//            String curfrom = "USD";
//            String  curto = "EGP";
//          
//           String  address = "http://rate-exchange.appspot.com/currency?from=" + curfrom + "&to=" + curto + "&q=" + snum;
//           url = new URL(address);
//           HttpURLConnection connect = (HttpURLConnection) url.openConnection();
//           BufferedReader   br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
//            String output = br.readLine();
//             Gson gson = new Gson();
//            r = gson.fromJson(output, CurrencyConvertResponse.class);
////            //System.out.println(r.getV());
////            //System.out.println(output); 
//        } catch (Exception e) {
//             //System.out.println(e);
////            result.setText("Error! Please check the Problem(e.g. Net)");
////            JOptionPane.showMessageDialog(null, "An Error has Occured!", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
//      return r.getV();
//    }
//     public  double SendRequestandConvert (int snum) 
//    
//    {
//         CurrencyConvertResponse r=new  CurrencyConvertResponse();
//       
//        String res;
//        URL url;
//        try {
//            String curfrom = "USD";
//            String  curto = "EGP";
//          
//           String  address = "http://rate-exchange.appspot.com/currency?from=" + curfrom + "&to=" + curto + "&q=" + snum;
//           url = new URL(address);
//           HttpURLConnection connect = (HttpURLConnection) url.openConnection();
//           BufferedReader   br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
//            String output = br.readLine();
//             Gson gson = new Gson();
//            r = gson.fromJson(output, CurrencyConvertResponse.class);
////            //System.out.println(r.getV());
////            //System.out.println(output); 
//        } catch (Exception e) {
//             //System.out.println(e);
////            result.setText("Error! Please check the Problem(e.g. Net)");
////            JOptionPane.showMessageDialog(null, "An Error has Occured!", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
//      return r.getRate();
//    }
    public double SendRequestandConvert(int snum) throws MalformedURLException, IOException {
        String from = "USD";
        String to = "EGP";
//http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=USD&ToCurrency=EGP
        java.net.URL url = new java.net.URL("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=" + from+ "&ToCurrency=" + to);
        java.util.Scanner sc = new java.util.Scanner(url.openStream());

        // <?xml version="1.0" encoding="utf-8"?>
        sc.nextLine();

        // <double xmlns="http://www.webserviceX.NET/">0.724</double>
        String str = sc.nextLine().replaceAll("^.*>(.*)<.*$", "$1");

        sc.close();

        Double rate = Double.parseDouble(str);
        return rate;

    }
}
