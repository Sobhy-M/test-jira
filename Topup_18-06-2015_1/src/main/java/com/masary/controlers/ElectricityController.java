package com.masary.controlers;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.dto.CommissionsTransaction;
import com.masary.database.dto.ElectricityBillWithCommissionsRepresentation;
import com.masary.database.dto.HttpResponseElectricity;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.manager.MasaryManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

/**
 *
 * @author aya
 */
public class ElectricityController extends HttpServlet
{

    public static Logger logger = Logger.getLogger(CONFIG.APP_LOG);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));

        action = request.getParameter(CONFIG.PARAM_ACTION);
        try
        {
            if (!isLogin(session))
            {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action)))
                {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            
            if (CONFIG.ACTION_CUSTOMER_Bill_Inquiry.equals(action))
            {
                String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                nextJSP = CONFIG.PAGE_Electricity_inquiry;
                session.setAttribute("serv_id", BTC);
            }
            else if (CONFIG.ACTION_Delta_Electricity_Inq.equals(action))
            {
                Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                session.setAttribute("BTC", bill_Type);
                String participant = request.getParameter("BILLING_ACCOUNT");
                HttpResponseElectricity hre = getBillInfo(participant);
                if (hre.getStatus() == 200)
                {
                    ElectricityBillWithCommissionsRepresentation[] list = gson.fromJson(hre.getMessage(), ElectricityBillWithCommissionsRepresentation[].class);
                    session.setAttribute("EBWCR", list);
                    nextJSP = CONFIG.PAGE_Electricity_info;
                }
                else
                {
                    nextJSP = CONFIG.PAGE_Electricity_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + hre.getStatus() + " " + hre.getMessage());
                }
            }
            else if (CONFIG.ACTION_Delta_Electricity_pay.equals(action))
            {
                String amount = request.getParameter(CONFIG.AMOUNT);
                HttpResponseElectricity ratePlaneResponse = getRatePlanInfo(String.valueOf(service_id), amount);
                if (ratePlaneResponse.getStatus() == 200)
                {
                    CommissionsTransaction commissionsTransaction = gson.fromJson(ratePlaneResponse.getMessage(), CommissionsTransaction.class);
                    session.setAttribute("CommTxn", commissionsTransaction);
                    nextJSP = CONFIG.PAGE_Electricity_PAYMENT;
                }
                else
                {
                    nextJSP = CONFIG.PAGE_Electricity_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + ratePlaneResponse.getStatus() + " " + ratePlaneResponse.getMessage());
                }
            }
            else
            {
                ElectricityBillWithCommissionsRepresentation[] list = (ElectricityBillWithCommissionsRepresentation[]) session.getAttribute("EBWCR");
                HttpResponseElectricity paymentResponse = payBill(list[0].getBillInquiryId());
                if (paymentResponse.getStatus() == 200)
                {
                    ElectricityBillWithCommissionsRepresentation resRepresentation = gson.fromJson(paymentResponse.getMessage(), ElectricityBillWithCommissionsRepresentation.class);
                    session.setAttribute("PayTxn", resRepresentation);
                    nextJSP = CONFIG.PAGE_Elect_PAY_confirm;
                }
                else
                {
                    nextJSP = CONFIG.PAGE_Electricity_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + paymentResponse.getStatus() + " " + paymentResponse.getMessage());
                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            //System.out.println("Bill_Controler : " + e);
        }
        finally
        {
            out.close();

        }
    }

    private boolean isLogin(HttpSession session)
    {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    public HttpResponseElectricity getBillInfo(String participant)
    {
        StringBuilder response = new StringBuilder();
        HttpResponseElectricity httpResponseElectricity = new HttpResponseElectricity();
        HttpURLConnection connection = null;
        BufferedReader in = null;

        URL url;
        try
        {
            // Public IP
//            url = new URL("http://196.218.51.74:9005/electricity-service/electricitybill/info/" + participant);
            
            // Local IP
            url = new URL("http://192.168.1.45:9005/electricity-service/electricitybill/info/" + participant);
            
            
//            url = new URL("http://127.0.0.1:9005/electricity-service/electricitybill/info/" + participant);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            logger.info(url);
        }
        catch (MalformedURLException ex)
        {
            java.util.logging.Logger.getLogger(ElectricityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            java.util.logging.Logger.getLogger(ElectricityController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String userCredentials = "shady:ragab";
        String basicAuth = null;
        try
        {
            basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userCredentials.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("deviceType", "Web");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //Send request
        int responseCode = 0;
        try
        {
            responseCode = connection.getResponseCode();

            logger.info(responseCode);
            if (responseCode == 200)
            {

                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                httpResponseElectricity.setMessage(response.toString());
                httpResponseElectricity.setStatus(responseCode);
            }
            else
            {
                Gson gson = new Gson();

                String inputLine;
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                logger.info(getReason(response.toString()));
                httpResponseElectricity.setMessage(handelError(getReason(response.toString())));

            }

            logger.info(response);
            logger.info(responseCode);
            in.close();

            //print result
        }
        catch (Exception e)
        {
            httpResponseElectricity.setStatus(-1);
            httpResponseElectricity.setMessage("خطا في العملية");
            //System.out.println("Exception" + e.getMessage());
            logger.error(e.getMessage());

        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return httpResponseElectricity;
    }

    public HttpResponseElectricity getRatePlanInfo(String serviceId, String amount)
    {
        StringBuilder response = new StringBuilder();
        HttpResponseElectricity httpResponseElectricity = new HttpResponseElectricity();
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try
        {
            //Create connection
            
//Public IP
//            URL url = new URL("http://196.218.51.74:9005/rateplanservice/rateplan/getCommissions/" + serviceId + "/" + amount);
           
            // Local IP
            URL url = new URL("http://192.168.1.45:9005/rateplanservice/rateplan/getCommissions/" + serviceId + "/" + amount);
            
//            URL url = new URL("http://127.0.0.1:9005/rateplanservice/rateplan/getCommissions/" + serviceId + "/" + amount);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Language", "Windows-1256");
            connection.setRequestProperty("charset", "Windows-1256");
            logger.info(url);
            String userCredentials = "shady:ragab";
            String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userCredentials.getBytes("UTF-8"));
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestProperty("deviceType", "Web");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //Send request
            int responseCode = connection.getResponseCode();
            logger.info(responseCode);
            if (responseCode == 200)
            {

                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                logger.info(response);
                httpResponseElectricity.setMessage(response.toString());
                httpResponseElectricity.setStatus(responseCode);
            }
            else
            {
                Gson gson = new Gson();

                String inputLine;
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                logger.info(getReason(response.toString()));
//                httpResponseElectricity = gson.fromJson(response.toString(), HttpResponseElectricity.class);
                httpResponseElectricity.setMessage(handelError(getReason(response.toString())));

            }
            //print result

            in.close();
        }
        catch (Exception e)
        {
            httpResponseElectricity.setStatus(-1);
            httpResponseElectricity.setMessage("خطا في العملية");
            logger.error(e.getMessage());
            e.printStackTrace();

        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return httpResponseElectricity;
    }

    public HttpResponseElectricity payBill(long payId)
    {
        StringBuilder response = new StringBuilder();
        HttpResponseElectricity httpResponseElectricity = new HttpResponseElectricity();
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try
        {
            //Create connection
            
            // Public IP
//            URL url = new URL("http://196.218.51.74:9005/electricity-service/electricitybill/pay/" + payId);
            
            // Local IP
            URL url = new URL("http://192.168.1.45:9005/electricity-service/electricitybill/pay/" + payId);
            
            
//            URL url = new URL("http://127.0.0.1:9005/electricity-service/electricitybill/pay/");
            logger.info(url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Language", "Windows-1256");
            connection.setRequestProperty("charset", "Windows-1256");
            // String userCredentials = topupRequest.getCustomerId() + ":" + topupRequest.getPassword();
            String userCredentials = "shady:ragab";
            String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userCredentials.getBytes("UTF-8"));
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestProperty("deviceType", "Web");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200)
            {

                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                logger.info(response);
                httpResponseElectricity.setMessage(response.toString());
                httpResponseElectricity.setStatus(responseCode);
            }
            else
            {
                Gson gson = new Gson();

                String inputLine;
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                logger.info(getReason(response.toString()));
//                httpResponseElectricity = gson.fromJson(response.toString(), HttpResponseElectricity.class);
                httpResponseElectricity.setMessage(handelError(getReason(response.toString())));

            }
            //print result
            in.close();
        }
        catch (Exception e)
        {
            httpResponseElectricity.setStatus(-1);
            httpResponseElectricity.setMessage("خطا في العملية");
            logger.error(e.getMessage());
            e.printStackTrace();

            return null;
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return httpResponseElectricity;
    }

    String handelError(String message)
    {
        String errormessage = "";
        switch (Integer.parseInt(message))
        {
            case 900:
                errormessage = CONFIG.billNotFoundMessage;
                break;
            case 904:
                errormessage = CONFIG.BillIsAlreadyPayedMessage;
                break;
            case 1:
                errormessage = CONFIG.accountInactiveMessage;
                break;
            case 2:
                errormessage = CONFIG.InternalServerErrorMessage;
                break;
            case 3:
                errormessage = CONFIG.InternalServerErrorMessage;
                break;
            case 4:
                errormessage = CONFIG.serviceIsInactiveMessage;
                break;
            case 5:
                errormessage = CONFIG.serviceIsInactiveAccountMessage;
                break;
            case 6:
                errormessage = CONFIG.transactionAmountnotInserviceRangeMessage;
                break;
            case 7:
                errormessage = CONFIG.serviceIsNotAvailableMessage;
                break;
            case 8:
                errormessage = CONFIG.globalTrxIdIsMissingMessage;
                break;
            case 9:
                errormessage = CONFIG.ledgerTransactionExceptionMessage;
                break;
            case 10:
                errormessage = CONFIG.inputParameterIsMissingMessage;
                break;
            case 11:
                errormessage = CONFIG.deviceTypeIsMissingMessage;
                break;
            case 12:
                errormessage = CONFIG.commonMasaryExceptionMessage;
                break;
            case 13:
                errormessage = CONFIG.rollBackExceptionMessage;
                break;
            case 14:
                errormessage = CONFIG.accountBalanceIsNotsuffcientMessage;
                break;
        }
        return errormessage;
    }

    private static String getReason(String response)
    {
        StringBuilder r = new StringBuilder();

        for (int i = 0; i < response.length(); ++i)
        {

            // to avoid tags
            if (response.charAt(i) == '<')
            {
                while (response.charAt(i) != '>')
                {
                    ++i;
                }
                ++i;
                // to Get the Value Between Tags
                StringBuilder line = new StringBuilder();
                while (response.charAt(i) != '<')
                {
                    line.append(response.charAt(i));
                    ++i;
                }

                //to check value is Number or not
                boolean isNum = true;
                for (int index = 0; index < line.length(); ++index)
                {
                    Character c = line.charAt(index);
                    if (!(c >= '0' && c <= '9'))
                    {
                        isNum = false;
                        break;
                    }
//                    if (Character.isAlphabetic(line.charAt(index))) {
//                        isNum = false;
//                        break;
//                    }
                }
                if (isNum && !line.toString().isEmpty())
                {
                    return line.toString();
                }
            }
        }
        return r.toString();
    }
}
