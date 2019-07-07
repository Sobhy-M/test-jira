/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.AddWallet;

import com.masary.integration.dto.AccountDTO;
import com.masary.integration.CreateAccountHttpURLConnection;
import NewSYS.Getinfo;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.dto.AddressDTO;
import com.masary.database.dto.CustomerProfile;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abdelsabor
 */
public class AddNewWalletAgentConfirmation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String addCustomers(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        try {
            request.setCharacterEncoding("UTF-8");
            ArrayList<String> service = new ArrayList<String>();
            CustomerProfile cp = new CustomerProfile();
            String str;
            String str2 =  request.getParameter("FullArabicName");
            cp.setCustomerPhone(request.getParameter(CONFIG.PARAM_MSISDN));
            cp.setGender(request.getParameter("gender"));
            cp.setAccountType(request.getParameter("AccountType"));
            

            cp.setCustomerName(request.getParameter("FullEnglishName"));
            cp.setCustomerArabicName(request.getParameter("FullArabicName"));
            cp.setBirthDate(request.getParameter("BirthDate"));
            cp.setNationalID(request.getParameter("NATIONAL_ID"));
            cp.setPreferredLang("ar");
            cp.setEmail(request.getParameter(CONFIG.PARAM_EMAIL_ADDRESS));

            AddressDTO homeAddress = new AddressDTO();
            AddressDTO workAddress = new AddressDTO();

            homeAddress.setAddressDetails(request.getParameter("FullAddress"));
            homeAddress.setGovernate(request.getParameter("HomeGovAddress"));
            homeAddress.setCity(request.getParameter("HomeCityAddress"));
            homeAddress.setRegion(request.getParameter("HomeRegionAddress"));

            workAddress.setAddressDetails(request.getParameter("WorkFullAddress"));
            workAddress.setGovernate(request.getParameter("WorkGovAddress"));
            workAddress.setCity(request.getParameter("WorkCityAddress"));
            workAddress.setRegion(request.getParameter("WorkRegionAddress"));

            cp.setHomeAddress(homeAddress);
            cp.setWorkAddress(workAddress);

            cp.setCustomerQuestion(request.getParameter("SecurityQuestion"));
            cp.setCustomerAnswer(request.getParameter("SecurityAnswer"));

            cp.setImage1Url(request.getParameter("ImageUrl_1"));
            cp.setImage2Url(request.getParameter("ImageUrl_2"));
            cp.setImage3Url(request.getParameter("ImageUrl_3"));
            cp.setImage4Url(request.getParameter("ImageUrl_4"));
            cp.setImage5Url(request.getParameter("ImageUrl_5"));

            cp.setCanAddEmp("N");
            cp.setAlternativePhone(request.getParameter(CONFIG.PARAM_MSISDN_ALTERNATIVE));
            cp.setLandLinePhone(request.getParameter(CONFIG.PARAM_LAND_LINE_PHONE));

            cp.setParentId(Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_PIN)));
            service.add("1");
            cp.setServiceList(service);

            cp.setShopName(request.getParameter("ShopName"));
            cp.setActivityType(request.getParameter("WorkType"));

            long custID = MasaryManager.getInstance().addCustomerFullDetails(cp);

            if (custID > 0) {
                nextJSP = CONFIG.PAGE_NEXT_ADD_CUSTOMER3;
            } else if (custID == -1) {
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("ErrorMessage", CONFIG.getCustomerExistsEx(session));
                throw new Exception("this customer already exists");
            } else if (custID == -400) {
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("ErrorMessage", CONFIG.getFailToUploadCustomerImagesEx(session));
                throw new Exception("Error During Upload Images");
            } else if (custID == -1000) {
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("ErrorMessage", CONFIG.getErrorMandatoryFields(session));
                throw new Exception("Error Missing Paramters");
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("ErrorMessage", CONFIG.getErrorTransaction(session));
                throw new Exception("Error During Adding Customer");
            }
            ////////////////////////////////////////////////////////
//            NewSYS.Getinfo info = new Getinfo();
            //select password from database 
//            info = MasaryManager.getInstance().getPasswordForNewCustomer(cp.getCustomerPhone());
            /////////////////////////////////////////////////////////////////
            ////////////call new system----Added by Nourhan 27-04-2016///////////////////////////////////
//            CreateAccountHttpURLConnection accountUrlConnection = new CreateAccountHttpURLConnection();
//            AccountDTO accountDTO = new AccountDTO();
//            accountDTO.setAccountId(String.valueOf(info.getCustomerID()));
//            accountDTO.setParent((String) session.getAttribute(CONFIG.PARAM_PIN));
//            accountDTO.setAccountName(request.getParameter(CONFIG.PARAM_USERNAME));
//            accountDTO.setAccountName(cp.getCustomerName());
//            accountDTO.setAddress(request.getParameter("FullAddress"));
//            accountDTO.setAlternativeMobile(request.getParameter(CONFIG.PARAM_MSISDN_ALTERNATIVE));
//            accountDTO.setAnswer(customerAnswer);
//            accountDTO.setBirthDate((request.getParameter(CONFIG.PARAM_BIRTH_DATE)));
//            accountDTO.setCountry(homeAddress.getCity());
//            accountDTO.setEmail(request.getParameter(CONFIG.PARAM_EMAIL_ADDRESS));//else types
//            accountDTO.setGovernerate(homeAddress.getGovernate());///sent fromm db 
//            accountDTO.setLandline(request.getParameter(CONFIG.PARAM_LAND_LINE_PHONE));
//            accountDTO.setNationalId(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
//            accountDTO.setmobileNumber(request.getParameter(CONFIG.PARAM_MSISDN));
//            //accountDTO.setPostalNumber(request.getParameter(CONFIG.PARAM_POSTAL_CODE));
//            accountDTO.setQuestion(request.getParameter("SecurityQuestion"));
//            accountDTO.setPassword(info.getPassword());   //update
//            accountDTO.setUserName(String.valueOf(info.getCustomerID())); //update
//            Gson gson = new Gson();
//            String requestjson = gson.toJson(accountDTO);
//
//            try {
//                String token=session.getAttribute("Token").toString();
//                accountUrlConnection.CreateAccount(requestjson,token);
//            } catch (Exception e) {
//                MasaryManager.logger.info("Error in New System in user creation");
//            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AddNewWalletAgentConfirmation.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("ErrorMessage", CONFIG.getFailToUploadCustomerImagesEx(session));
            nextJSP = CONFIG.PAGE_ERRPR;
        } catch (Exception ex) {
            Logger.getLogger(AddNewWalletAgentConfirmation.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("ErrorMessage", CONFIG.getFailToUploadCustomerImagesEx(session));
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            String nextJSP = addCustomers(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
