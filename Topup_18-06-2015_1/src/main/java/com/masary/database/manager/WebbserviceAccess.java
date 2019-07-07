package com.masary.database.manager;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Melad
 */
public class WebbserviceAccess {

    /* Example usage:
     *
     * WebbserviceAccess wsa = new WebserviceAccess();
     * wsa.setServiceAddress("http://localhost/SkyViewDemo/SkyViewDemo.asmx");
     * wsa.setServiceName("adding");
     * wsa.addParameter("i", "2");
     * wsa.addParameter("j", "3");
     * wsa.addParameter("k", "4");
     * wsa.addParameter("l", "5");
     * String result = wsa.sendRequest();
     */
    /** Creates a new instance of WebbserviceAccess */
    private String m_ServiceAddress; // the address of the .asmx file
    public String m_ServiceName; // the name of the service
    private Vector m_ParamNames; // input parameter names
    private Vector m_ParamData; // input parameter values
    boolean m_AutoParseResponse; // Regulates if actual respose value or the entire xml should be returned.

    public WebbserviceAccess() {
        m_ServiceAddress = "";
        m_ServiceName = "";
        m_AutoParseResponse = true;
        clearParameters();
    }

    void clearParameters() {
        m_ParamNames = new Vector(); // input parameter values
        m_ParamData = new Vector(); // input parameter values
    }

    void setServiceAddress(String ServiceAddress) {
        m_ServiceAddress = ServiceAddress;
    }

    void setServiceName(String ServiceName) {
        m_ServiceName = ServiceName;
    }

    void setAutoParseResponse(boolean DoAutoParseResponse) {
        m_AutoParseResponse = DoAutoParseResponse;
    }

    void addParameter(String Name, String Data) {
        m_ParamNames.addElement((Object) Name);
        m_ParamData.addElement((Object) Data);
    }

    String sendRequest() throws Exception {
        String Response = "";
        try {
// Send the webbservice request.
            URL url = new URL(m_ServiceAddress + "/" + m_ServiceName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            ////System.out.println(url);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            //Parameters passed to the method are added here
            String args = "";
            for (int t = 0; t < m_ParamNames.size(); t++) {
                String nameAndValue = (String) m_ParamNames.elementAt(t) + "=" + URLEncoder.encode((String) m_ParamData.elementAt(t), "UTF-8");
                if (t != m_ParamNames.size() - 1) {
                    nameAndValue += "&";
                }
                args += nameAndValue;
            }
// ////System.out.println( args );
            out.println(args);
            out.close();

// Recieve the webbservice result.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                Response += line;
            }
            in.close();
        } catch (Exception e) {
            throw new Exception("Error calling webservice:\n" + e.getMessage());
        }

        if (m_AutoParseResponse) {
            return ParseResponse(Response);
        } else {
            return Response;
        }
    }

    private String ParseResponse(String Response) {
// Simple string parsing to get the acctual value
        String ResponseValue = "";
// Find first right-bracket
        int index = Response.indexOf(">");
        if (index != -1) {
// Find second right-bracket
            index = Response.indexOf(">", index + 1);
            if (index != -1) {
// Find the closest following left-bracket
                int EndIndex = Response.indexOf("<", index);
                if (EndIndex != -1) {
                    ResponseValue = Response.substring(index + 1, EndIndex);
                }
            }
        }
        return ResponseValue;
    }
}

