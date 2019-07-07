package com.masary.controlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.HttpClients;

import com.masary.controlers.CashatMasary.CashatMasaryProperties;
import com.masary.controlers.CashatMerchant.CashatMerchantProperties;
import com.masary.controlers.GoBus.GoBusProperties;
import com.masary.integration.AcceptClient;
import com.masary.integration.CashatClient;
import com.masary.integration.CashatMerchantClient;
import com.masary.integration.GoBusHttpClient;
import com.masary.integration.TEDataClient;
import com.masary.integration.UpdateProfileClient;
import com.masary.integration.dto.AcceptPaymentResponse;
import com.masary.integration.dto.CashatPaymentResponse;
import com.masary.integration.dto.ElectricityPaymentResponse;
import com.masary.integration.dto.FastLinkPaymentResponse;
import com.masary.integration.dto.GoBusPaymentRepresentation;
import com.masary.integration.dto.TEData_Payment_Response;
import com.masary.integration.dto.UpdateProfileRequest;
import com.masary.integration.dto.UpdateProfileResponse;
import com.masary.utils.SystemSettingsUtil;

import NewSYS.GetAccountInfo;
import NewSYS.Getinfo;
import NewSYS.UpdateAccount;
import NewSYS.getinfoDTO;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.controlers.WEPostPaid.WePostPaidProperties;
import com.masary.controllers.AlexWater.AlexWaterProperities;
import com.masary.controllers.VodafoneDSL.VodafoneDSLProperties;
import com.masary.controllers.ZaidElKhier.ZaidElKhierProperties;
import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.BulkSMSReportDTO;
import com.masary.database.dto.BulkVoucherDTO;
import com.masary.database.dto.CustomerDTO;
import com.masary.database.dto.CustomerProfile;
import com.masary.database.dto.CustomerServiceDTO;
import com.masary.database.dto.LoginDto;
import com.masary.database.dto.Masary_Bill_Receipt;
import com.masary.database.dto.SellVoucherResponse;
import com.masary.database.dto.TransactionDTO;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.BeINSportsClient;
import com.masary.integration.CNEClient;
import com.masary.integration.EtisalatGiftsClient;
import com.masary.integration.EgyptEyesClient;
import com.masary.integration.ElMesbahElModeaClient;
import com.masary.integration.FastLinkClient;
import com.masary.integration.KafrElShiekhWaterClient;
import com.masary.integration.Hospital500500Client;
import com.masary.integration.OrangeCorporateBillsClient;
import com.masary.integration.OrangeGiftsClient;

import com.masary.integration.SonaaElkherClient;

import com.masary.integration.RealEstateClient;

import com.masary.integration.TamweelClient;
import com.masary.integration.ValuClient;
import com.masary.integration.TEDataBillsClient;
import com.masary.integration.TransactionsMenuClient;
import com.masary.integration.VodafoneTopupDslClient;
import com.masary.integration.dto.AccountDTO;
import com.masary.integration.dto.BeINSportsPaymentResponseDTO;
import com.masary.integration.dto.CNEPaymentResponseDTO;
import com.masary.integration.dto.EtisalatGiftsPaymentRepresentation;
import com.masary.integration.dto.KafrElShiekhWaterPaymentResponse;
import com.masary.integration.dto.OrangeCorporatePaymentResponse;
import com.masary.integration.dto.OrangeGiftsPaymentRepresentation;

import com.masary.integration.dto.Representation;

import com.masary.integration.dto.RealEstatePaymentRepresentation;

import com.masary.integration.dto.TamweelPaymentRepresentation;
import com.masary.integration.dto.ValuPaymentResponse;
import com.masary.integration.dto.TEDataBillsResponse;
import com.masary.integration.dto.TransactionsMenuResponse;
import com.masary.integration.dto.VodafoneDslPaymentResponse;
import com.masary.utils.HttpServletRequestProxy;
import java.util.ResourceBundle;

/**
 *
 * @author omnya
 */
public class WalletServices_Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            if (CONFIG.ACTION_AGENT_OPERATIONS.equals(action)) {
                nextJSP = agentOperations(request);
            } else if (CONFIG.ACTION_CUSTOMER_OPERATIONS.equals(action)) {
                nextJSP = customerOperations(request);
            }
        } catch (Exception e) {
            MasaryManager.logger.info("processGetRequest nextJSP= " + nextJSP + " action= " + action + " Client ID=" + session.getAttribute(CONFIG.PARAM_PIN));
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stacktrace = sw.toString();
            MasaryManager.logger.info("processGetRequest Full Stack Error= " + stacktrace);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

            MasaryManager.logger.error(e);
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }

    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            if (CONFIG.ACTION_ADD_CUSTOMER.equals(action)) {
                nextJSP = addCustomers(request);
            } else if (CONFIG.ACTION_MANAGE_AGENT.equals(action)) {
                nextJSP = manageAgent(request);
            } else if (CONFIG.ACTION_ASIGN_CUSTOMER_BALANCE.equals(action)) {
                nextJSP = assignCustomerBalance(request);
            } else if (CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE.equals(action)) {
                nextJSP = manageServiceBalance(request);
            } else if (CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE_V.equals(action)) {
                nextJSP = manageServiceBalanceVoucher(request);
            } else if (CONFIG.ACTION_GET_LAST_TRANSACTIONS.equals(action)) {
                nextJSP = getLastTransactions(request);
            } else if (CONFIG.ACTION_ASSIGN_VOUCHER_TO_CUSTOMER.equals(action)) {
                nextJSP = AssignVoucherTOCustomer(request);
            } else if (CONFIG.ACTION_ASSIGN_AGENT_SERVICE_BALANCE.equals(action)) {
                nextJSP = assignServiceBalance(request);
            } else if (CONFIG.ACTION_ASIGN_AGENT_SERVICE_BALANCE.equals(action)) {
                nextJSP = assignAgentServiceBalance(request);
            } else if (CONFIG.ACTION_ASSIGN_REP_NEW_BALANCE.equals(action)) {
                nextJSP = CONFIG.PAGE_ASSIGN_NEW_REP_BALANCE;
            } else if (CONFIG.ACTION_ASIGN_AGENT_BALANCE.equals(action)) {
                nextJSP = assignAgentBalance(request);
            } else if (CONFIG.ACTION_INCLUDE_TRANS_AG.equals(action)) {
                nextJSP = transList(request);
            } else if (CONFIG.ACTION_INCLUDE_Earn_List.equals(action)) {
                nextJSP = EarningList(request);
            } else if (CONFIG.ACTION_TREE_LIST_TRANS.equals(action)) {
                nextJSP = treeList(request);
            } else if (CONFIG.ACTION_CUST_TRANSFER.equals(action)) {
                nextJSP = customerTransfer(request);
            } else if (CONFIG.ACTION_CUSTOMER_PAY_BILL.equals(action)) {
                nextJSP = payBill(request);
            } else if (CONFIG.ACTION_ADD_EMPLOYEE.equals(action)) {
                nextJSP = addEmployees(request);
            } else if (CONFIG.ACTION_CHANGE_EMPLOYEE_STATE.equals(action)) {
                nextJSP = changeEmployeeState(request);
            } else if (CONFIG.ACTION_MANAGE_ROLES.equals(action)) {
                nextJSP = mangeRoles(request);
            } else if (CONFIG.ACTION_CHANGE_LANG.equals(action)) {
                nextJSP = changeLanguage(request);
            } else if (CONFIG.ACTION_CHANGE_LANG_MOBLIE.equals(action)) {
                nextJSP = changeLanguageMobile(request);
            } else if (CONFIG.ACTION_BALANCE_MOBILE.equals(action)) {
                nextJSP = balanceMobile();
            } else if (CONFIG.ACTION_REPORTS_MOBLIE.equals(action)) {
                nextJSP = CONFIG.PAGE_REPS_REPORT_MOBILE;
            } else if (CONFIG.ACTION_SEND_MAIL_REQUEST.equals(action)) {
                nextJSP = sendMailRequest(request);
            } else if (CONFIG.ACTION_OPEN_VFCASH.equals(action)) {
                nextJSP = openVfVash(request);
            } else if (CONFIG.GET_AGENT_TO.equals(action)) {          // Nourhan_Get Payed Name in masary transfer
                AgentTo(request, response);
            } else if (CONFIG.ADD_TRANSFER_FEES.equals(action)) {
                addTransferFees(request, response);
            } else if (CONFIG.ACTION_EXPORT_TO_EXCEL.equals(action)) {          // OMNYA_21-09-2014_Export Transaction List to Excel
                nextJSP = CONFIG.PAGE_EXPORT_TRANS_LIST_EXCEL;
            } else if (CONFIG.ACTION_EXPORT_TO_EXCEL_1.equals(action)) {          // OMNYA_21-09-2014_Export Transaction List to Excel
                nextJSP = CONFIG.PAGE_EXPORT_TRANS_LIST_EXCEL_1;
            } else if (CONFIG.ACTION_Print_Vouvher.equals(action)) {
                try {
                    String trxID = request.getParameter(CONFIG.PARAM_Transaction_ID);
                    String sellType = request.getParameter(CONFIG.PARAM_SELL_TYPE);
                    String to = request.getParameter(CONFIG.PARAM_MSISDN);
                    if (trxID != null) {
                        String custId = null;
                        if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                            custId = request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString();
                        } else {
                            custId = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
                        }
                        SellVoucherResponse voucherResponse = MasaryManager.getInstance().getVoucher(custId, trxID);
                        request.setAttribute(CONFIG.PARAM_Transaction_ID, trxID);
                        request.setAttribute(CONFIG.PARAM_TAX_AMOUNT, voucherResponse.getVoucherInfo().getDenom());
                        request.setAttribute(CONFIG.PARAM_SELL_TYPE, sellType);
                        request.setAttribute("second", "second");
                        request.setAttribute("email", to);
                        if (sellType.trim().equals("Send E-Mail") || sellType.trim().equals("Export IN Excel")) {
                            ArrayList<BulkVoucherDTO> voucherInfoList = new ArrayList<BulkVoucherDTO>();
                            ArrayList<SellVoucherResponse> voucherList = new ArrayList<SellVoucherResponse>();
                            voucherInfoList.add(voucherResponse.getVoucherInfo());
                            voucherList.add(voucherResponse);
                            session.setAttribute("voucherList", voucherList);
                            session.setAttribute("voucherInfoList", voucherInfoList);
                            if (sellType.trim().equals("Send E-Mail")) {
                                nextJSP = CONFIG.PAGE_SEND_MAIL;
                            } else if (sellType.trim().equals("Export IN Excel")) {
                                nextJSP = CONFIG.PAGE_EXPORT_EXCEL;
                            }
                        } else {
                            session.setAttribute("voucherResponse", voucherResponse);
                            if (voucherResponse.getVoucherSerial().size() == 1) {
                                nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_Printing;
                                MasaryManager.getInstance().updateVoucher(trxID);
                            } else {
                                nextJSP = CONFIG.PAGE_VIEW_VOUCHER_SERIAL;
                            }

                        }
                    }
                } catch (Exception ex) {
                    // ===Amir 17-8-2015=== add logs to check the Null Pointer Exception Line
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                    MasaryManager.logger.info("processPostRequest nextJSP= " + nextJSP + " action= " + action + " Client ID=" + session.getAttribute(CONFIG.PARAM_PIN));
                    StringWriter sw = new StringWriter();
                    ex.printStackTrace(new PrintWriter(sw));
                    String stacktrace = sw.toString();
                    MasaryManager.logger.info("processPostRequest Full Stack Error= " + stacktrace);
                    //=========END========
                    MasaryManager.logger.error(ex.getMessage());
                }
            } else if (CONFIG.ACTION_Print_Vouvher_2.equals(action)) {
                String serial = request.getParameter("voucher_serial");
                String trxID = request.getParameter(CONFIG.PARAM_Transaction_ID);
                String sellType = request.getParameter(CONFIG.PARAM_SELL_TYPE);
                if (trxID != null) {
                    String custId = null;
                    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                        custId = request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString();
                    } else {
                        custId = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
                    }
                    SellVoucherResponse voucherResponse = MasaryManager.getInstance().getVoucher(custId, trxID);
                    request.setAttribute(CONFIG.PARAM_SELL_TYPE, sellType);
                    request.setAttribute(CONFIG.PARAM_Transaction_ID, trxID);
                    request.setAttribute(CONFIG.PARAM_TAX_AMOUNT, voucherResponse.getVoucherInfo().getDenom());
                    if (voucherResponse.getVoucherSerial().size() > 1 && serial == null) {
                        session.setAttribute("voucherResponse", voucherResponse);
                        nextJSP = CONFIG.PAGE_VIEW_VOUCHER_SERIAL;
                    } else if (serial != null && !serial.equals("All") && voucherResponse.getVoucherSerial().size() > 1) {
                        for (int i = 0; i < voucherResponse.getVoucherSerial().size(); i++) {
                            if (voucherResponse.getVoucherSerial().get(i).equals(serial)) {
                                ArrayList<String> voucherPinList = new ArrayList<String>();
                                ArrayList<String> voucherSerialList = new ArrayList<String>();
                                voucherPinList.add(voucherResponse.getVoucherPin().get(i));
                                voucherSerialList.add(voucherResponse.getVoucherSerial().get(i));
                                voucherResponse.setVoucherPin(voucherPinList);
                                voucherResponse.setVoucherSerial(voucherSerialList);
                                break;
                            }
                        }
                        session.setAttribute("voucherResponse", voucherResponse);

                        nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_RePrinting;
                    } else {
                        session.setAttribute("voucherResponse", voucherResponse);
                        nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_RePrinting;
                    }
                    MasaryManager.getInstance().updateVoucher(trxID);
                }
            } else if (CONFIG.ACTION_Print_Reciept.equals(action)) {
                Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
                session.removeAttribute("transaction_id");
                session.removeAttribute("serv_id");
                session.setAttribute("transaction_id", requestParameters.get("transaction_id"));
                request.setAttribute("secondPrinted", requestParameters.get("secondPrinted"));
                String trxTypeID = requestParameters.get("serv_id");
//                int ledger_id = Integer.parseInt(request.getParameter("LedgerID"));
                long ledger_id = Long.parseLong(requestParameters.get("LEDGER"));
                session.setAttribute("serv_id", trxTypeID);
                Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();

                if (trxTypeID.equals("124") || trxTypeID.equals("111") || trxTypeID.equals("112") || trxTypeID.equals("99008") || trxTypeID.equals("115") || trxTypeID.equals("618") || trxTypeID.equals("619") || trxTypeID.equals("9999") || trxTypeID.equals("242") || trxTypeID.equals("600") || trxTypeID.equals("599") || trxTypeID.equals("598") || trxTypeID.equals("211") || trxTypeID.equals("23011") || trxTypeID.equals("2006") || trxTypeID.equals("23112") || trxTypeID.equals("23116") || trxTypeID.equals("23114") || trxTypeID.equals("23113") || trxTypeID.equals("23111") || trxTypeID.equals("23115") || trxTypeID.equals("6130") || trxTypeID.equals("44401") || trxTypeID.equals("44402")) {

                    if (trxTypeID.equals("599")) {
                        receipt = MasaryManager.getInstance().getNewSystemAboElRishReceipt(ledger_id);
                        session.setAttribute("receipt", receipt);

                    } else if (trxTypeID.equals("23112") || trxTypeID.equals("23116") || trxTypeID.equals("23114") || trxTypeID.equals("23113") || trxTypeID.equals("23111") || trxTypeID.equals("23115")) {
                        AcceptClient acceptClient = new AcceptClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        request.setAttribute("trxTypeID", trxTypeID);
                        AcceptPaymentResponse acceptPaymentResponse = acceptClient.acceptReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr(), trxTypeID);
                        request.setAttribute("acceptPaymentResponse", acceptPaymentResponse);
                        nextJSP = CONFIG.PAGE_ACCEPT_REPRINT;
                    } else if (trxTypeID.equals("211")) {
                        GoBusHttpClient goBusHttpClient = new GoBusHttpClient(HttpClients.createDefault());
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        GoBusPaymentRepresentation goBusPaymentRepresentation = goBusHttpClient.doGoBusReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("goBusPaymentRepresentation", goBusPaymentRepresentation);
                        nextJSP = GoBusProperties.goBusReprintPage;
                    } else if (trxTypeID.equals("23011")) {
                        BeINSportsClient beINSportsClient = new BeINSportsClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        BeINSportsPaymentResponseDTO beINSportsPaymentResponse = beINSportsClient.beINSportsReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("beINSportsPaymentResponse", beINSportsPaymentResponse);
                        nextJSP = CONFIG.PAGE_beINSports_Reprint;

                    } else if (trxTypeID.equals("2006")) {
                        TEDataClient tedataClient = new TEDataClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        TEData_Payment_Response tedataPaymentResponse = tedataClient.tedataReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("tedataPaymentResponse", tedataPaymentResponse);
                        nextJSP = CONFIG.TEData_Reprint_Page;

                    } else if (trxTypeID.equals("6130")) {
                        FastLinkClient fastLinkClient = new FastLinkClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        FastLinkPaymentResponse fastLinkPaymentResponse = fastLinkClient.fastLinkReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("fastLinkPaymentResponse", fastLinkPaymentResponse);
                        nextJSP = CONFIG.PAGE_FastLink_Reprint;

                    } else if (trxTypeID.equals("44401")) {
                        CashatClient cashatClient = new CashatClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        CashatPaymentResponse cashatPaymentResponse = cashatClient.cashatReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("cashatPaymentResponse", cashatPaymentResponse);
                        nextJSP = CashatMasaryProperties.Page_CashatMasary_Reprint;
                    } else if (trxTypeID.equals("44402")) {
                        CashatMerchantClient cashatClient = new CashatMerchantClient();
                        String lang = request.getSession().getAttribute("lang").toString();
                        String token = request.getSession().getAttribute("Token").toString();
                        CashatPaymentResponse cashatPaymentResponse = cashatClient.cashatReprint(String.valueOf(ledger_id), lang, token, request.getRemoteAddr());
                        request.setAttribute("cashatPaymentResponse", cashatPaymentResponse);
                        nextJSP = CashatMerchantProperties.Page_CashatMerchant_Reprint;
                    } else if (trxTypeID.equals("600")) {
                        receipt = MasaryManager.getInstance().getNewSystemSECIReceipt(ledger_id);
                        session.setAttribute("receipt", receipt);

                    } else if (trxTypeID.equals("618")) {
                        receipt = MasaryManager.getInstance().getNewSystemPetrotradeBillsReceipt(ledger_id);

                        session.setAttribute("receipt", receipt);
                    } else if (trxTypeID.equals("619")) {
                        receipt = MasaryManager.getInstance().getNewSystemPetrotradeCounterReceipt(ledger_id);

                        session.setAttribute("receipt", receipt);

                    } else if (trxTypeID.equals("242")) {
                        receipt = MasaryManager.getInstance().getNewSystemWePostPaidReceipt(ledger_id);
                        session.setAttribute("receipt", receipt);
                    } else if (trxTypeID.equals("9999")) {
                        receipt = MasaryManager.getInstance().getNewSystemAlexWaterReceipt(ledger_id);
                        session.setAttribute("receipt", receipt);
                    } else if (trxTypeID.equals("598")) {
                        receipt = MasaryManager.getInstance().getNewSystemZaidElKhierReceipt(ledger_id);
                        session.setAttribute("receipt", receipt);
                    } else {
                        receipt = MasaryManager.getInstance().getOnlineBillsReceipt(Integer.parseInt(requestParameters.get("transaction_id")));
                        session.setAttribute("receipt", receipt);
                    }
                    if (trxTypeID.equals("618") || trxTypeID.equals("619")) {
                        nextJSP = CONFIG.PAGE_PETROTRADE_Reprint;
                    } else if (trxTypeID.equals("111") || trxTypeID.equals("112") || trxTypeID.equals("124") || trxTypeID.equals("115")) {
                        nextJSP = CONFIG.Page_Reprint_Bills;
                    } else if (trxTypeID.equals("9999")) {
                        nextJSP = AlexWaterProperities.Page_AlexWater_Reprint;
                    } else if (trxTypeID.equals("242")) {
                        nextJSP = WePostPaidProperties.Page_WePostPaid_Reprint;
                    } else if (trxTypeID.equals("600")) {
                        request.setAttribute("receipt", receipt);
                        nextJSP = CONFIG.SECI_Reprint_Page;
                    } else if (trxTypeID.equals("599")) {
                        request.setAttribute("receipt", receipt);
                        nextJSP = CONFIG.AboElRish_Reprint_Page;
                    } else if (trxTypeID.equals("23011")) {
                        nextJSP = CONFIG.PAGE_beINSports_Reprint;
                    } else if (trxTypeID.equals("598")) {
                        nextJSP = ZaidElKhierProperties.Page_ZaidElKhier_Reprint;
                    } else if (trxTypeID.equals("211")) {
                        nextJSP = GoBusProperties.goBusReprintPage;
                    } else if (trxTypeID.equals("23112") || trxTypeID.equals("23116") || trxTypeID.equals("23114") || trxTypeID.equals("23113") || trxTypeID.equals("23111") || trxTypeID.equals("23115")) {
                        nextJSP = CONFIG.PAGE_ACCEPT_REPRINT;
                    } else if (trxTypeID.equals("2006")) {
                        nextJSP = CONFIG.TEData_Reprint_Page;
                    } else if (trxTypeID.equals("6130")) {
                        nextJSP = CONFIG.PAGE_FastLink_Reprint;
                    } else if (trxTypeID.equals("44401")) {
                        nextJSP = CashatMasaryProperties.Page_CashatMasary_Reprint;
                    } else if (trxTypeID.equals("44402")) {
                        nextJSP = CashatMerchantProperties.Page_CashatMerchant_Reprint;
                    }

                } else if (trxTypeID.equals("125")) {
                    receipt = MasaryManager.getInstance().getTE_Recharge_Receipt(Integer.parseInt(requestParameters.get("transaction_id")));
                    session.setAttribute("receipt", receipt);
                    nextJSP = CONFIG.PAGE_reprint;
                } else if (trxTypeID.equals("99012") || trxTypeID.equals("99013") || trxTypeID.equals("99014") || trxTypeID.equals("99015")
                        || trxTypeID.equals("99018") || trxTypeID.equals("99019") || trxTypeID.equals("99019") || trxTypeID.equals("99021") || trxTypeID.equals("99022") || trxTypeID.equals("99007")) {
                    ElectricityPaymentResponse electricityPaymentResponse = MasaryManager.getInstance().reprintElectricityReceipt(ledger_id);
                    session.setAttribute("receipt", electricityPaymentResponse);
                    session.setAttribute("SERVICE_ID", String.valueOf(electricityPaymentResponse.getTransactionId()));
                    nextJSP = CONFIG.PAGE_ElectricityReprint;
                } else if (trxTypeID.equals("1010")) {
                    VodafoneTopupDslClient VodafoneTopupDslClient = new VodafoneTopupDslClient();
                    VodafoneDslPaymentResponse vodafoneDslReprintResponse = VodafoneTopupDslClient.reprint(ledger_id, request.getSession().getAttribute("lang").toString(), request.getSession().getAttribute("Token").toString());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    Date date1 = new Date(vodafoneDslReprintResponse.getUpdateTime());
                    String date = dateFormat.format(date1);
                    String time = timeFormat.format(date1);
                    session.setAttribute("time", time);
                    session.setAttribute("date", date);
                    session.setAttribute("vodafoneDslReprintResponse", vodafoneDslReprintResponse);
                    nextJSP = VodafoneDSLProperties.Page_VodafoneDsl_Reprint;
                } else if (trxTypeID.equals("666")) {
                    OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClient();
                    OrangeCorporatePaymentResponse orangeCorporateReprintResponse = orangeCorporateBillsClient.orangeCorporateReprint(ledger_id, request.getSession().getAttribute("lang").toString(), request.getSession().getAttribute("Token").toString());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    Date date1 = new Date(orangeCorporateReprintResponse.getUpdateDate());
                    String date = dateFormat.format(date1);
                    String time = timeFormat.format(date1);
                    session.setAttribute("time", time);
                    session.setAttribute("date", date);
                    session.setAttribute("orangeCorporateReprintResponse", orangeCorporateReprintResponse);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("PAGE_OrangeCorporateBills_REPRINT");
                } else if (trxTypeID.equals("667")) {
                    OrangeGiftsClient orangeGiftsClient = new OrangeGiftsClient();

                    OrangeGiftsPaymentRepresentation reprintResponse = orangeGiftsClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("reprintResponse", reprintResponse);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_OrangeGifts_Reprint");
                } else if (trxTypeID.equals("668")) {
                    EtisalatGiftsClient etisalatGiftsClient = new EtisalatGiftsClient();
                    EtisalatGiftsPaymentRepresentation reprintResponse = etisalatGiftsClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    Date dateValue = new Date(reprintResponse.getInsertDate());
                    session.setAttribute("reprintResponse", reprintResponse);
                    request.setAttribute("dateValue", dateValue);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_EtisalatGifts_Reprint");
                } else if (trxTypeID.equals("117")) {
                    TEDataBillsClient teDataBillsClient = new TEDataBillsClient();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    TEDataBillsResponse reprintResponse = teDataBillsClient.rePrint(ledger_id, session.getAttribute("Token").toString());
                    Date date1 = new Date(reprintResponse.getInsertDate());
                    String date = dateFormat.format(date1);
                    String time = timeFormat.format(date1);
                    session.setAttribute("time", time);
                    session.setAttribute("date", date);
                    session.setAttribute("reprintResponse", reprintResponse);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    if (reprintResponse.getSystemResource().trim().equals("0")) {
                        nextJSP = rb.getString("Page_TeDataBills_ReprintSimba");
                    } else {
                        nextJSP = rb.getString("Page_TeDataBills_ReprintBss");
                    }

                } else if (trxTypeID.equals("201")) {
                    TamweelClient tamweelClient = new TamweelClient();
                    TamweelPaymentRepresentation tamweelPaymentRepresentation = tamweelClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());

                    Date dateValue = new Date(tamweelPaymentRepresentation.getExecutionDate());

                    request.setAttribute("dateValue", dateValue);
                    request.setAttribute("tamweelPaymentRepresentation", tamweelPaymentRepresentation);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_Tamweel_ReprintPage");

                } else if (trxTypeID.equals("32")) {
                    ValuClient valuClient = new ValuClient();
                    ValuPaymentResponse valuPaymentResponse = valuClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    Date dateValue = new java.util.Date(valuPaymentResponse.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    session.setAttribute("valuPaymentResponse", valuPaymentResponse);
                    nextJSP = CONFIG.PAGE_VALU_REPRINT;

                } else if (trxTypeID.equals("2526")) {
                    RealEstateClient realEstateClient = new RealEstateClient();
                    RealEstatePaymentRepresentation realEstatePaymentRepresentation = realEstateClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    Date dateValue = new Date(realEstatePaymentRepresentation.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    session.setAttribute("paymentRepresentation", realEstatePaymentRepresentation);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_RealEstate_Reprint");

                } else if (trxTypeID.equals("66")) {
                    KafrElShiekhWaterClient kafrElShiekhWaterClient = new KafrElShiekhWaterClient();
                    KafrElShiekhWaterPaymentResponse kafrElShiekhWaterPaymentResponse = kafrElShiekhWaterClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    Date date = new Date(kafrElShiekhWaterPaymentResponse.getInsertDate());
                    request.setAttribute("date", date);
                    request.setAttribute("kafrElShiekhWaterPaymentResponse", kafrElShiekhWaterPaymentResponse);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_KafrElShiekhWater_Reprint");
                } else if (trxTypeID.equals("55")) {
                    ElMesbahElModeaClient elMesbahElModeaClient = new ElMesbahElModeaClient();

                    Representation representation = elMesbahElModeaClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("representation", representation);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    Date dateValue = new java.util.Date(representation.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    nextJSP = rb.getString("Page_ElMesbahElModea_ReprintPage");
                } else if (trxTypeID.equals("514")) {
                    SonaaElkherClient sonaaElkherClient = new SonaaElkherClient();

                    Representation representation = sonaaElkherClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("representation", representation);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    Date dateValue = new java.util.Date(representation.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    nextJSP = rb.getString("Page_SonaaElkher_ReprintPage");
                } else if (trxTypeID.equals("512")) {
                    EgyptEyesClient egyptEyesClient = new EgyptEyesClient();

                    Representation representation = egyptEyesClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("representation", representation);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    Date dateValue = new java.util.Date(representation.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    nextJSP = rb.getString("Page_egyptEyes_ReprintPage");
                } else if (trxTypeID.equals("513")) {
                    Hospital500500Client hospital500500Client = new Hospital500500Client();
                    Representation representation = hospital500500Client.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("representation", representation);
                    Date dateValue = new java.util.Date(representation.getInsertDate());
                    request.setAttribute("dateValue", dateValue);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_Hospital500500_ReprintPage");
                } else if (trxTypeID.equals("9962")) {
                    CNEClient cneClient = new CNEClient();
                    CNEPaymentResponseDTO representation = cneClient.reprint(ledger_id, session.getAttribute("lang").toString(), session.getAttribute("Token").toString());
                    session.setAttribute("paymentResponse", representation);
                    Date dateValue = new Date(representation.getCoverageEndDate());
                    request.setAttribute("dateValue", dateValue);
                    ResourceBundle rb = ResourceBundle.getBundle("Bundle");
                    nextJSP = rb.getString("Page_CNE_Reprint");
                } else {
                    receipt = MasaryManager.getInstance().getDonationReceipt(Integer.parseInt(requestParameters.get("transaction_id")));
                    String date = receipt.getTransaction_date().substring(0, 10);
                    String time = receipt.getTransaction_date().substring(11, 16);
                    request.setAttribute("date", date);
                    request.setAttribute("time", time);
                    session.setAttribute("receipt", receipt);
                    nextJSP = CONFIG.PAGE_reprint;

                }
            } else if (CONFIG.ACTION_Show_Report.equals(action)) {   //added by keroles
                String transid = request.getParameter("transaction_id");
                List<BulkSMSReportDTO> bulkSMSReportlist = new ArrayList<BulkSMSReportDTO>();
                try {
                    bulkSMSReportlist = MasaryManager.getInstance().getBulkSMSReport(Long.parseLong(transid));
                    session.setAttribute("BulkList", bulkSMSReportlist);
                    nextJSP = CONFIG.PAGE_View_bulk_report;
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex.getStackTrace());
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }

            try {
                session.setAttribute(CONFIG.PAGE, nextJSP);
            } catch (Exception e) {
                MasaryManager.logger.error(e.getStackTrace());
                session.setAttribute("ErrorMessage", e.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;

            }
            // ---------Amir 16-8-2015 ---- Add Logging
            MasaryManager.logger.info("processPostRequest nextJSP= " + nextJSP + " action= " + action + " Client ID=" + session.getAttribute(CONFIG.PARAM_PIN));
        } catch (Exception e) {
            // ===Amir 17-8-2015=== add logs to check the Null Pointer Exception Line
            MasaryManager.logger.info("processPostRequest nextJSP= " + nextJSP + " action= " + action + " Client ID=" + session.getAttribute(CONFIG.PARAM_PIN));
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stacktrace = sw.toString();
            MasaryManager.logger.info("processPostRequest Full Stack Error= " + stacktrace);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

            //====END
            MasaryManager.logger.error(e);
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }

    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    private String openVfVash(HttpServletRequest request) {
        String nextJSP = " ";
        String role = request.getParameter("role");
        nextJSP = role + ".jsp";
        return nextJSP;
    }

    private String balanceMobile() {
        String nextJSP = CONFIG.PAGE_BALANCE_MOBILE;
        return nextJSP;
    }

    private String changeLanguage(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String lang = request.getParameter(CONFIG.lang);
        String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
        MasaryManager.logger.info(action + " " + lang + " IP " + request.getRemoteAddr());
        if (lang != null) {
            session.setAttribute(CONFIG.lang, lang);
        } else {
            session.setAttribute(CONFIG.lang, (session.getAttribute(CONFIG.lang).equals("")) ? "ar" : "");
        }
        if (role == null) {
            nextJSP = "";
        } else {
            nextJSP = (String) session.getAttribute(CONFIG.PAGE);
        }
        return nextJSP;
    }

    private String changeLanguageMobile(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.lang, (session.getAttribute(CONFIG.lang).equals("")) ? "ar" : "");
        nextJSP = CONFIG.PAGE_MAIN_MOBILE;
        MasaryManager.logger.info(action + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String mangeRoles(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        Enumeration e = request.getParameterNames();
        List<String> roles = new ArrayList<String>();
        String roleName,
                roleValue;
        while (e.hasMoreElements()) {
            roleName = (String) e.nextElement();
            roleValue
                    = request.getParameter(roleName);
            if (roleValue.equalsIgnoreCase("on")) {
                roles.add(roleName);
            }
        }
        String custID = (String) session.getAttribute(CONFIG.PARAM_CUSTOMER_ID);
        MasaryManager.getInstance().setRoles(custID, roles);
        nextJSP = CONFIG.PAGE_VIEW_CUSTOMERS;
        return nextJSP;
    }

    private String assignCustomerBalance(HttpServletRequest request) {
        Enumeration e = request.getParameterNames();
        String name = null, custId = null;
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        try {
            while (e.hasMoreElements()) {
                custId = (String) e.nextElement();
//                try {
//                    byte[] bytes = request.getParameter(custId).getBytes("ISO-8859-1");
//                    name = new String(bytes, "UTF-8");
                name = request.getParameter(custId);
//                } catch (UnsupportedEncodingException ex) {
//                    MasaryManager.logger.error(ex.getMessage());
//                }
                if (name.equals(CONFIG.getAssign(session))) {
                    break;
                }
                if (name.equals(CONFIG.getDeactivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(custId, false);
                    nextJSP = CONFIG.PAGE_VIEW_CUSTOMERS;
                }
                if (name.equals(CONFIG.getActivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(custId, true);
                    nextJSP = CONFIG.PAGE_VIEW_CUSTOMERS;
                }
            }
            MasaryManager.logger.info(action + " " + custId + " " + name + " IP " + request.getRemoteAddr());
        } catch (SQLException ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        if (!(nextJSP.equals(CONFIG.PAGE_VIEW_CUSTOMERS))) {
            String balance = request.getParameter(CONFIG.PARAM_BALANCE);
            if (balance == null) {
                session.setAttribute(CONFIG.PARAM_CUSTOMER_ID, custId);
                nextJSP = CONFIG.PAGE_ASSIGN_CUSTOMER;
            } else {
                custId = (String) session.getAttribute(CONFIG.PARAM_CUSTOMER_ID);
                String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                try {
                    String transId = String.valueOf(MasaryManager.getInstance().addBalance(custId, id, balance));
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                    request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                    nextJSP = CONFIG.PAGE_VIEW_AGENT_TRANSACTION;
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex);
                    session.setAttribute("ErrorMessage",
                            ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
        }
        return nextJSP;
    }

    private String addEmployees(HttpServletRequest request) {
        String nextJSP = "";
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter(CONFIG.PARAM_ACTION);
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter(CONFIG.PARAM_USERNAME);
//            byte[] bytes = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC).getBytes("ISO-8859-1");
//            String usernameArabic = new String(bytes, "UTF-8");
            String usernameArabic = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC);
            String phone = request.getParameter(CONFIG.PARAM_MSISDN);
            String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
            String confirm = request.getParameter(CONFIG.CONFIRM);
            session.setAttribute(CONFIG.PARAM_USERNAME_ARABIC, usernameArabic);
            MasaryManager.logger.info(action + " " + username + " IP " + request.getRemoteAddr());
            if (confirm != null) {
                nextJSP = CONFIG.PAGE_ADD_EMPLOYEE_CONFIRM;
                request.removeAttribute(CONFIG.CONFIRM);
            } else {
                try {
                    if (username.isEmpty() || phone.isEmpty()) {
                        throw new Exception(CONFIG.getFillInAllData(session));
                    }
                    MasaryManager.getInstance().addEmployee(id, username, usernameArabic, phone, "N", "N", "Where did you meet your spouse?", "any"/*, request*/);
                    nextJSP = "3.jsp";
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex);
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
        } catch (UnsupportedEncodingException ex) {
            MasaryManager.logger.error(ex);
        }
        return nextJSP;
    }

    private String customerOperations(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String btn = request.getParameter(CONFIG.PARAM_CUSTOMER_BTN);
        MasaryManager.logger.info(action + " " + btn + " IP " + request.getRemoteAddr());
        if (btn.equals("Bulk Voucher")) {
            nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_BULK_VOUCHER;
        }
        if (btn.equals("Money Transfer")) {
            MasaryManager.logger.info("Money Transfer");
            nextJSP = CONFIG.PAGE_CUSTOMER_TRANSACTION;
        } else if (btn.equals("Bills Payment")) {
            nextJSP = CONFIG.PAGE_CUSTOMER_BILLS;
        } else if (btn.equals("Manage Customer Account")) {
            MasaryManager.logger.info("Manage Customer Account");
            nextJSP = CONFIG.PAGE_MANAGE_CUSTOMER_ACCOUNT;
        } else if (btn.equals("Send Mail")) {
            nextJSP = CONFIG.PAGE_SEND_MAIL_REQUEST;
            request.setAttribute("TYPE", request.getParameter("TYPE"));
        } else if (btn.equals("View Transactions")) {
            request.getSession().removeAttribute("include");
            String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            String empID = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
            session.setAttribute("custIdForAccSession", agentID);
            request.setAttribute("fromEmployeeOnly", "no");
            MasaryManager.logger.info("View Transactions");

            nextJSP = CONFIG.PAGE_VIEW_BANK_ACCOUNT;
        } else if (btn.equals("Recharge")) {
            nextJSP = CONFIG.PAGE_RECHARGE;
        } else if (btn.equals("Services")) {
            nextJSP = CONFIG.PAGE_VIEW_AGENT_SERVICES;
        } else if (btn.equals("Add Employee")) {
            nextJSP = CONFIG.PAGE_ADD_EMPLOYEE;
        } else if (btn.equals("Employee List")) {
            MasaryManager.logger.info("Employee List");
            nextJSP = CONFIG.PAGE_VIEW_EMPLOYEES;
        } else if (btn.equals("View Transactions Employee")) {
            MasaryManager.logger.info("View Transactions Employee");
            request.getSession().removeAttribute("include");
            request.getSession().removeAttribute("transFromAg");
            request.getSession().removeAttribute("transToAg");
            String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            String empID = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
            session.setAttribute("custIdForAccSession", agentID);
            List transFromAg = transFromAg = MasaryManager.getInstance().getTransactionsByPayerForEmployee(agentID, empID);
            request.getSession().setAttribute("transFromAg", transFromAg);
            request.getSession().setAttribute("transToAg", null);
            request.setAttribute("fromEmployeeOnly", "yes");
            nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_ACCOUNT;
        }
        return nextJSP;
    }

    private String sendMailRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String recipient = "";
        String nextJSP = "";
        String subject = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        request.setCharacterEncoding("UTF-8");
        String messageBody = request.getParameter("messageBody");
        String type = request.getParameter("TYPE");
        String id = null;
        if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString().equals("-1")) {
            id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        } else {
            id = (String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
        }
        String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
        if (type.contains("Purchasing Order")) {
            recipient = CONFIG.sent_mail_Purchasing_Order_recipient;
            subject = "Order from customer-" + id;
        } else if (type.contains("Refund Trx")) {
            recipient = CONFIG.sent_mail_Refund_Trx_recipient;
            subject = "Pending transactions from customer-" + id;
        } else if (type.contains("Help Request")) {
            recipient = CONFIG.sent_mail_Help_Request_recipient;
            subject = "Help request from customer-" + id;
        }
        MasaryManager.logger.info(action + "sendMailRequest" + id + " subject: " + subject + " messageBody: " + messageBody + " IP " + request.getRemoteAddr());
        try {
            MasaryManager.getInstance().sendMail(recipient, subject, messageBody);
            nextJSP = role + ".jsp";
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String changeEmployeeState(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String name = null;
        String custId = null;
        nextJSP = "";
        try {
            while (e.hasMoreElements()) {
                custId = (String) e.nextElement();
//                try {
//                    byte[] bytes = request.getParameter(custId).getBytes("ISO-8859-1");
                name = request.getParameter(custId);
//                } catch (UnsupportedEncodingException ex) {
//                    MasaryManager.logger.error(ex.getMessage());
//                }
                if (name.equals(CONFIG.getDeactivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(custId, false);
                    nextJSP = CONFIG.PAGE_VIEW_EMPLOYEES;
                }
                if (name.equals(CONFIG.getActivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(custId, true);
                    nextJSP = CONFIG.PAGE_VIEW_EMPLOYEES;
                }
                if (name.equals(CONFIG.getResetPassword(session))) {
                    String password = MasaryManager.getInstance().resetEmployeePassword(custId);
                    action = request.getParameter(CONFIG.PARAM_ACTION);
                    String empMobile = request.getParameter("employeePhone");
                    MasaryManager.getInstance().sendSMS(empMobile, "Your new password is " + password + " please login and change it ");
                    nextJSP = CONFIG.PAGE_VIEW_EMPLOYEES;
                }
            }
            MasaryManager.logger.info(action + " " + custId + " " + name + " IP " + request.getRemoteAddr());
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String payBill(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        Enumeration e = request.getParameterNames();
        String name = null, billId = null;
        while (e.hasMoreElements()) {
            billId = (String) e.nextElement();
            name
                    = request.getParameter(billId);
            if (name.equals(CONFIG.PAY)) {
                break;
            }
        }
        String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
        try {
            String transId = String.valueOf(MasaryManager.getInstance().payBill(custID, billId));
            session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
            request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_CUSTOMER_BILLS);
            nextJSP
                    = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute(
                    "ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String customerTransfer(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String amount = request.getParameter(CONFIG.AMOUNT);
        String payedPin = request.getParameter(CONFIG.PARAM_PAYED_PIN);
        String fees = request.getParameter(CONFIG.FEES);
        MasaryManager.logger.info(action + " " + payedPin + " IP " + request.getRemoteAddr());
        try {
            if (Double.parseDouble(amount) < 1) {
                throw new Exception(CONFIG.getBalanceError(session));
            }
            if (payedPin.equals(id)) {
                throw new Exception(CONFIG.getCanntTransferError(session));
            }
            if (Double.parseDouble(payedPin) < 1) {
                throw new Exception(CONFIG.getNotValidId(session, false));
            }
            if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().isEmployee(payedPin)) {
                throw new Exception("لا يمكن اجراء عملية تحويل مصاري الى هذا الموظف");
            }
            String transId = String.valueOf(MasaryManager.getInstance().addBalance(payedPin, id, amount));
            if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                MasaryManager.getInstance().insertEmployeeTransactions(session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString(), transId);
            }
            session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
            session.setAttribute(CONFIG.PARAM_TAX_AMOUNT, fees);
            session.setAttribute(CONFIG.AMOUNT, amount);
            request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
            nextJSP = CONFIG.PAGE_TRANSFER_FEES_RESULT;
        } catch (NumberFormatException ex) {
            MasaryManager.logger.error(ex);
            if (ex.getMessage().contains(amount)) {
                session.setAttribute("ErrorMessage", CONFIG.getNotValidId(session, true));
            } else {
                session.setAttribute("ErrorMessage", CONFIG.getNotValidId(session, false));
            }

            nextJSP = CONFIG.PAGE_ERRPR;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            if (ex.getMessage().contains("ORA-20139")) {
                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                nextJSP = CONFIG.PAGE_ERRPR_SSO;
            } else {
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        }
        return nextJSP;
    }

    private String addCustomers(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String valid = request.getParameter(CONFIG.VALID);
        if (valid != null) {
            String mobileNo = request.getParameter(CONFIG.PARAM_MSISDN);
            if (MasaryManager.getInstance().isExistInMasary(mobileNo)) {
                session.setAttribute("ErrorMessage", ((String) session.getAttribute(CONFIG.lang)).equals("ar") ? CONFIG.Mobile_ErorrAR : CONFIG.Mobile_ErorrEN);
                nextJSP = CONFIG.PAGE_ADD_CUSTOMER;
            } else {
                nextJSP = CONFIG.PAGE_NEXT_ADD_CUSTOMER;
            }
        } else {
            try {
                request.setCharacterEncoding("UTF-8");
                ArrayList<String> service = new ArrayList<String>();
                CustomerProfile cp = new CustomerProfile();
                cp.setCustomerName(request.getParameter(CONFIG.PARAM_USERNAME));
//                byte[] bytes = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC).getBytes("ISO-8859-1");
//                String usernameArabic = new String(bytes, "UTF-8");
                String usernameArabic = request.getParameter(CONFIG.PARAM_USERNAME);
                cp.setCustomerArabicName(usernameArabic);
                cp.setCustomerQuestion(request.getParameter(CONFIG.PARAM_QUESTION));
//                bytes = request.getParameter(CONFIG.PARAM_ANSWER).getBytes("ISO-8859-1");
                String customerAnswer = request.getParameter(CONFIG.PARAM_ANSWER);
                cp.setCustomerAnswer(customerAnswer);
                cp.setCustomerPhone(request.getParameter(CONFIG.PARAM_MSISDN));
                String canAddEmployee = ("on".equals(request.getParameter(CONFIG.PARAM_ADD_EMPLOYEE)) ? "Y" : "N");
                cp.setCanAddEmp(canAddEmployee);
                cp.setAlternativePhone(request.getParameter(CONFIG.PARAM_MSISDN_ALTERNATIVE));
                cp.setLandLinePhone(request.getParameter(CONFIG.PARAM_LAND_LINE_PHONE));
                cp.setEmail(request.getParameter(CONFIG.PARAM_EMAIL_ADDRESS));
                cp.setAlternativeEmail(request.getParameter(CONFIG.PARAM_EMAIL_ADDRESS_ALTERNATIVE));
//                bytes = request.getParameter(CONFIG.PARAM_ADDRESS1).getBytes("ISO-8859-1");
                String address1 = request.getParameter(CONFIG.PARAM_ADDRESS1);
                cp.setAddress1(address1);
                if (!request.getParameter(CONFIG.PARAM_ADDRESS2).isEmpty()) {
//                    bytes = request.getParameter(CONFIG.PARAM_ADDRESS2).getBytes("ISO-8859-1");
                    String address2 = request.getParameter(CONFIG.PARAM_ADDRESS2);
                    cp.setAddress2(address2);
                }
                cp.setCountry(request.getParameter(CONFIG.PARAM_COUNTRY));
                cp.setGovernorate(request.getParameter(CONFIG.PARAM_GOVERNORTE));
//                bytes = request.getParameter(CONFIG.PARAM_CITY).getBytes("ISO-8859-1");
                String city = request.getParameter(CONFIG.PARAM_CITY);
                cp.setCity(city);
                cp.setPostelCode(request.getParameter(CONFIG.PARAM_POSTAL_CODE));
                cp.setNationalID(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
                cp.setPreferredLang(request.getParameter(CONFIG.PARAM_PREFERRED_LANGUAGE));
                cp.setBirthDate(request.getParameter(CONFIG.PARAM_BIRTH_DATE));
                String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                cp.setParentId(Integer.parseInt(id));
                String next = request.getParameter(CONFIG.NEXT);
                if (next != null) {
                    nextJSP = CONFIG.PAGE_NEXT_ADD_CUSTOMER3;
                    request.removeAttribute(CONFIG.NEXT);
                } else {
                    Enumeration parameters = request.getParameterNames();
                    while (parameters.hasMoreElements()) {
                        String serviceId = (String) parameters.nextElement();
                        String serviceValue = request.getParameter(serviceId);
                        if ("on".equals(serviceValue) && serviceId.matches("[-+]?\\d+(\\.\\d+)?")) {
                            service.add(serviceId);
                        }
                    }
                    cp.setServiceList(service);
                    String confirm = request.getParameter(CONFIG.CONFIRM);
                    MasaryManager.logger.info(action + " " + cp.getCustomerName() + " IP " + request.getRemoteAddr());
                    if (confirm != null) {
                        nextJSP = CONFIG.PAGE_ADD_CUSTOMER_CONFIRM;
                        request.removeAttribute(CONFIG.CONFIRM);
                    } else {
                        try {
                            if (cp.getCustomerName().isEmpty() || cp.getCustomerQuestion().isEmpty() || cp.getCustomerAnswer().isEmpty() || cp.getCustomerPhone().isEmpty()
                                    || cp.getEmail().isEmpty() || cp.getNationalID().isEmpty() || cp.getAddress1().isEmpty() || cp.getCountry().isEmpty() || cp.getGovernorate().isEmpty()) {
                                throw new Exception(CONFIG.getFillInAllData(session));
                            }
                            MasaryManager.getInstance().addCustomerFullDetails(cp);

                            ////////////////////////////////
                            NewSYS.Getinfo info = new Getinfo();                             ////////////////////////////////////////////////////////
//                             select password from database 
                            info = MasaryManager.getInstance().getPasswordForNewCustomer(cp.getCustomerPhone());
                            /////////////////////////////////////////////////////////////////
                            ////////////call new system----Added by Nourhan 27-04-2016///////////////////////////////////
//                            CreateAccountHttpURLConnection account = new CreateAccountHttpURLConnection();
//                            AccountDTO accountDTO = new AccountDTO();
////                          accountDTO.setAccountCategory(Long.MIN_VALUE);
//                            accountDTO.setAccountId(String.valueOf(info.getCustomerID()));
////                            accountDTO.setAccountStatus("confirm");
////                            accountDTO.setAccountType("confirm");
////                          accountDTO.setRatePlanId(Long.MIN_VALUE);
//                            accountDTO.setAccountName(request.getParameter(CONFIG.PARAM_USERNAME));
//                            accountDTO.setAddress(address1);
//                            accountDTO.setAlternativeMobile(request.getParameter(CONFIG.PARAM_MSISDN_ALTERNATIVE));
//                            accountDTO.setAnswer(customerAnswer);
//                            accountDTO.setBirthDate((request.getParameter(CONFIG.PARAM_BIRTH_DATE)));
//                            accountDTO.setCountry(request.getParameter(CONFIG.PARAM_COUNTRY));
//                            accountDTO.setEmail(request.getParameter(CONFIG.PARAM_EMAIL_ADDRESS));//else types
//                            accountDTO.setGovernerate(request.getParameter(CONFIG.PARAM_GOVERNORTE));///sent fromm db 
//                            accountDTO.setLandline(request.getParameter(CONFIG.PARAM_LAND_LINE_PHONE));
//                            accountDTO.setNationalId(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
//                            accountDTO.setmobileNumber(request.getParameter(CONFIG.PARAM_MSISDN));
//                            accountDTO.setPostalNumber(request.getParameter(CONFIG.PARAM_POSTAL_CODE));
//                            accountDTO.setQuestion(request.getParameter(CONFIG.PARAM_QUESTION));
//                            accountDTO.setPassword(info.getPassword());   //update
//                            accountDTO.setUserName(String.valueOf(info.getCustomerID())); //update
//
//                            Gson gson = new Gson();
//                            String requestjson = gson.toJson(accountDTO);
//                            try {
//                                account.CreateAccount(requestjson,null);
//                            } catch (Exception e) {
//
//                                MasaryManager.logger.info("Error in New System in user creation");
//                            }

                            ////
//                            Handle Exceptions if http response not equal 200
                            //////////////////////////////////////////////////
                            nextJSP = "2.jsp";
                        } catch (Exception ex) {
                            MasaryManager.logger.error(ex);
                            if (ex.getMessage().contains("ORA-20139")) {
                                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                                nextJSP = CONFIG.PAGE_ERRPR_SSO;
                            } //                        request.setAttribute("ErrorMessage", ex.getMessage());
                            else {
                                session.setAttribute("ErrorMessage", ((String) session.getAttribute(CONFIG.lang)).equals("ar") ? CONFIG.INTERNALERRORar : CONFIG.INTERNALERRORen);
                                nextJSP = CONFIG.PAGE_ERRPR;
                            }
                        }
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                MasaryManager.logger.error(ex);
            }
        }
        return nextJSP;
    }

    private String getLastTransactions(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String transId = request.getParameter(CONFIG.PARAM_Transaction_ID);

        if (transId == null) {
            nextJSP = CONFIG.PAGE_LASTRANSACTIONS_MOBILE;
        } else {
            session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
            request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_CUSTOMER_BILLS);
//                        nextJSP =CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
            nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_MOBILE;
        }
        MasaryManager.logger.info(action + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String manageServiceBalanceVoucher(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        int serviceId;
        serviceId = Integer.parseInt(request.getParameter("serv").toString());
        if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
            nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_BALANCE_MOBILE;
        } else {
            nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_BALANCE_V;
        }
        MasaryManager.logger.info(action + " " + serviceId + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String assignAgentServiceBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String customerId = request.getParameter(CONFIG.PARAM_PAYED_PIN);
        String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
        String balance = request.getParameter(CONFIG.PARAM_BALANCE);
        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String confirm = request.getParameter(CONFIG.CONFIRM);
        String employeeID = (String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
        nextJSP = "";
        if (confirm != null) {
            try {
                if (!employeeID.equals("-1")) {
                    if (MasaryManager.getInstance().isEmployee(customerId)) {
                        throw new Exception("لا يمكن اجراء عملية تخصيص رصيد الى هذا الموظف");
                    }
                    if (MasaryManager.getInstance().getAllowedServiceBalance(employeeID, Integer.parseInt(serviceId)) < Integer.parseInt(balance)) {
                        throw new Exception("رصيدك الحالي في هذه الخدمه لا يسمح باجراء العمليه");
                    }
                }
                nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_TO_AGENT_BALANCE_CONFIRMATION;
                request.removeAttribute(CONFIG.CONFIRM);
            } catch (Exception ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        } else {
            try {
                if (!employeeID.equals("-1")) {
                    if (MasaryManager.getInstance().isEmployee(customerId)) {
                        throw new Exception("لا يمكن اجراء عملية تخصيص رصيد الى هذا الموظف");
                    }
                    if (MasaryManager.getInstance().getAllowedServiceBalance(employeeID, Integer.parseInt(serviceId)) < Integer.parseInt(balance)) {
                        throw new Exception("رصيدك الحالي في هذه الخدمه لا يسمح باجراء العمليه");
                    }
                }
                String transId = String.valueOf(MasaryManager.getInstance().addBalanceToAgentService(id, customerId, serviceId, balance));
                session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
            } catch (Exception ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        }
        MasaryManager.logger.info(action + " " + customerId + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String AssignVoucherTOCustomer(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        try {
            int serviceId = Integer.parseInt(request.getParameter("serv").toString());
            double value = Double.parseDouble(request.getParameter("value").toString());
            int Amount = Integer.parseInt(request.getParameter("amount").toString());
            int Customer_To = Integer.parseInt(request.getParameter("Cust_Id").toString());
            int available = Integer.parseInt(request.getParameter("available").toString());
            AgentDTO agent_To = MasaryManager.getInstance().getAgent(String.valueOf(Customer_To));
            AgentDTO agent_From = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
            if (agent_To.isAutoAllocate().equals("F")) {
                MasaryManager.logger.info("لا يمكن تخصيص كروت الى هذ العميل" + request.getRemoteAddr());
                throw new Exception("لا يمكن تخصيص كروت الى هذا العميل");
            }
            if ((!agent_From.isIsActive()) || (!agent_To.isIsActive())) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error this agent is inactive IP " + request.getRemoteAddr());
                throw new Exception(CONFIG.getAccountNotAcctiveMessageV(session));
            }
            if (MasaryManager.getInstance().isEmployee(request.getParameter("Cust_Id")) && !session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                MasaryManager.logger.info("لا يمكن تخصيص كروت الى هذا الموظف" + request.getRemoteAddr());
                throw new Exception("لا يمكن تخصيص كروت الى هذا الموظف");
            }
            if (Amount <= 0) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error Amount can't less than Zero IP " + request.getRemoteAddr());
                throw new Exception(CONFIG.getBalanceError(session));
            }
            if (Amount > available) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error No Voucher With this Amount Available  IP " + request.getRemoteAddr());
                throw new Exception(CONFIG.getVoucherAvalaible(session));
            }
            if (request.getParameter("amount").toString().indexOf(".") > 0) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error Amount can't be fraction IP " + request.getRemoteAddr());
                throw new Exception(CONFIG.getValidAmountError(session));
            }
            if (request.getParameter("Cust_Id").toString().indexOf(".") > 0) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error Invalid Customer ID : " + request.getParameter("Cust_Id").toString() + " IP " + request.getRemoteAddr());
                throw new Exception(" Invalid Customer ID :" + request.getParameter("Cust_Id").toString());
            }
            int find = 0;
            for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent_To.getServices()) {
                if (service.getServiceID() == Integer.parseInt(request.getParameter("serv").toString())) {
                    find = 1;
                }
            }
            if (find == 0) {
                MasaryManager.logger.info("Assign Voucher To Customer Result : Error The Customer You Try To Assign Voucher Hasn't this service IP " + request.getRemoteAddr());
                throw new Exception(CONFIG.getCustomerToHasNoServiceError(session));
            }
            MasaryManager.logger.info(action + " " + serviceId + " IP " + request.getRemoteAddr());
            MasaryManager.getInstance().assignVoucherToCustomer(session, serviceId, value, Amount, Customer_To, Integer.parseInt(agent_From.getPin()));
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute(
                    "ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String manageServiceBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String serviceId = null;
        String name;
        while (e.hasMoreElements()) {
            serviceId = (String) e.nextElement();
            name = request.getParameter(serviceId);
            byte[] bytes;
//            try {
//                bytes = request.getParameter(serviceId).getBytes("ISO-8859-1");
//                name = new String(bytes, "UTF-8");
            name = request.getParameter(serviceId);
//            } catch (UnsupportedEncodingException ex) {
//                MasaryManager.logger.error(ex);
//            }
            if (CONFIG.getAssign(session).equals(name)) {
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
                    nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_BALANCE_MOBILE;
                } else {
                    nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_BALANCE;
                }
            }
            if (CONFIG.getAssignToAgent(session).equals(name)) {
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
                    nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_BALANCE_TO_AGENT_MOBILE;
                } else {
                    nextJSP = CONFIG.PAGE_ASSIGN_SERVICE_TO_AGENT_BALANCE;
                }
            }
        }
        MasaryManager.logger.info(action + " " + serviceId + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String agentOperations(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String btn = request.getParameter(CONFIG.PARAM_AGENT_BTN);
        MasaryManager.logger.info(action + " " + btn + " IP " + request.getRemoteAddr());
        if (btn.equals("Add Customer")) {
            nextJSP = CONFIG.PAGE_ADD_CUSTOMER;
        } else if (btn.equals("View Customers")) {
            nextJSP = CONFIG.PAGE_VIEW_CUSTOMERS;
        } else if (btn.equals("Manage Agent Account")) {
            nextJSP = CONFIG.PAGE_MANAGE_ACCOUNT;
        } else if (btn.equals("View Transactions")) {
            request.getSession().removeAttribute("include");
            String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            session.setAttribute("custIdForAccSession", agentID);
            nextJSP = (Integer) request.getSession().getAttribute("showTree") == 0
                    ? CONFIG.PAGE_VIEW_BANK_ACCOUNT : CONFIG.PAGE_VIEW_TREE_TRANSACTION;
        } else if (btn.equals("View Tree Transactions")) {
            request.getSession().removeAttribute("include");
            String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            session.setAttribute("custIdForAccSession", agentID);
            nextJSP = CONFIG.PAGE_VIEW_TREE_TRANSACTION;
        } else if (btn.equals("Services")) {
            nextJSP = CONFIG.PAGE_VIEW_AGENT_SERVICES;
        } else if (btn.equals("Reps")) {
            nextJSP = CONFIG.PAGE_VIEW_REPS_BALANCES;
        } else if (btn.equals("Show Reps List")) {
            nextJSP = CONFIG.PAGE_VIEW_REPS_LIST;
        } else if (btn.equals("Assign New Rep Balance")) {
            nextJSP = CONFIG.PAGE_ASSIGN_NEW_REP_BALANCE;
        } else if (btn.equals("View EarningReport")) {
            nextJSP = CONFIG.PAGE_VIEW_BANK_ACCOUNT_1;
        }
        return nextJSP;
    }

    private String transList(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        List transAg = new ArrayList();
        request.getSession().removeAttribute("include");
        request.getSession().removeAttribute("transFromAg");
        String agentID = (String) request.getSession().getAttribute("custIdForAccSession");
        String LogId = (String) request.getSession().getAttribute("custIdForAccSession");
        String trxType = request.getParameter(CONFIG.PARAM_TRANSACTION_TYPE);
        ArrayList<String> services = new ArrayList<String>();
        String dateFrom = request.getParameter("From");
        String dateTo = request.getParameter("To");
        Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
//        Enumeration parameters = request.getParameterNames();
        boolean isValidReprintVoucher = true;
        session.setAttribute("isValidReprintVoucher", isValidReprintVoucher);
        for (Map.Entry<String, String> parameter : requestParameters.entrySet()) {
            String serviceId = parameter.getKey();
            String serviceValue = parameter.getValue();
            if ("on".equals(serviceValue) && serviceId.matches("[-+]?\\d+(\\.\\d+)?")) {
                services.add(serviceId);
            }
        }
        try {

            String token = session.getAttribute("Token").toString();
            String ip = session.getAttribute("ip").toString();

            TransactionsMenuClient client = new TransactionsMenuClient();
            session.setAttribute("custIdForAccSession", agentID);

            if (services.size() > 1 && trxType.equals("ALL")) {
                transAg = MasaryManager.getInstance().getTransactionsList(agentID, dateFrom, dateTo, services, trxType, LogId);
                //add new client
                TransactionDTO trans = null;
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-mm-yyyy");
                Date date = (Date) newFormat.parse(dateFrom);
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                String dateFromD = formatter.format(date);
                Date date1 = (Date) newFormat.parse(dateTo);
                String dateToD = formatter.format(date1);
                try {
                    TransactionsMenuResponse[] transactionMenuResponse = client.viewTransactionsbyPayer(agentID, dateFromD, dateToD, token, ip);

                    Map<Integer, TransactionsMenuResponse> transactionMenuMap = new HashMap<Integer, TransactionsMenuResponse>();

                    for (TransactionsMenuResponse resp : transactionMenuResponse) {
                        transactionMenuMap.put(resp.getLedgerTrxId(), resp);
                    }

                    TransactionsMenuResponse transactionsMenuResp = new TransactionsMenuResponse();
                    if (transactionMenuResponse != null && transactionMenuResponse.length != 0) {
                        for (int i = 0; i < transAg.size(); i++) {
                            trans = (TransactionDTO) transAg.get(i);
                            if (trans.getLedgerID() != 0) {
                                transactionsMenuResp = transactionMenuMap.get(trans.getLedgerID());
                                trans.setCustomerPayedName(transactionsMenuResp.getPayed());
                            }
                        }
                    }
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex.getMessage());
                }

            } else if (services.size() == 1) {
                transAg = MasaryManager.getInstance().getTransactionsList(agentID, dateFrom, dateTo, services, trxType, LogId);
                //add new client
                TransactionDTO trans = null;
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-mm-yyyy");
                Date date = (Date) newFormat.parse(dateFrom);
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                String dateFromD = formatter.format(date);
                Date date1 = (Date) newFormat.parse(dateTo);
                String dateToD = formatter.format(date1);

                try {
                    TransactionsMenuResponse[] resp = client.viewTransactionsbyPayerAndServiceID(services.get(0), agentID, dateFromD, dateToD, token, ip);

                    Map<Integer, TransactionsMenuResponse> transactionMenuMap = new HashMap<Integer, TransactionsMenuResponse>();

                    for (TransactionsMenuResponse response : resp) {
                        transactionMenuMap.put(response.getLedgerTrxId(), response);
                    }

                    TransactionsMenuResponse transactionsMenuResp = new TransactionsMenuResponse();
                    if (resp != null && resp.length != 0) {
                        for (int i = 0; i < transAg.size(); i++) {
                            trans = (TransactionDTO) transAg.get(i);
                            if (trans.getLedgerID() != 0) {
                                transactionsMenuResp = transactionMenuMap.get(trans.getLedgerID());
                                trans.setCustomerPayedName(transactionsMenuResp.getPayed());
                            }
                        }
                    }
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex.getMessage());
                }

            }
            session.setAttribute("sumOfTrans", MasaryManager.getInstance().getSumOfTrx());
            session.setAttribute("transFromAg", transAg);
            session.setAttribute("include", "transFromAg");
            nextJSP = CONFIG.PAGE_VIEW_BANK_ACCOUNT;
            MasaryManager.logger.info(action + " " + agentID + " IP " + request.getRemoteAddr());

        } catch (Exception ex) {
            if (ex.getMessage().contains("ORA-20139")) {
                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                nextJSP = CONFIG.PAGE_ERRPR_SSO;
            } else {
                session.setAttribute("ErrorMessage", ex.getMessage());
                MasaryManager.logger.error(ex.getMessage());

                nextJSP = CONFIG.PAGE_ERRPR;
            }
        }
        return nextJSP;
    }

    private String EarningList(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String customer_id = session.getAttribute(CONFIG.PARAM_PIN).toString();
        List transAg = new ArrayList();
        request.getSession().removeAttribute("include");
        request.getSession().removeAttribute("transFromAg_1");
        String agentID = request.getParameter("Cust_Id");
        String trxType = request.getParameter(CONFIG.PARAM_TRANSACTION_TYPE);
        ArrayList<String> services = new ArrayList<String>();
        String dateFrom = request.getParameter("From");
        String dateTo = request.getParameter("To");
        Enumeration parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String serviceId = (String) parameters.nextElement();
            String serviceValue = request.getParameter(serviceId);
            if ("on".equals(serviceValue) && serviceId.matches("[-+]?\\d+(\\.\\d+)?")) {
                services.add(serviceId);
            }
        }
        try {
            transAg = MasaryManager.getInstance().getEarnList(customer_id, dateFrom, dateTo);

            session.setAttribute("transFromAg_1", transAg);
            session.setAttribute("include", "transFromAg_1");
            nextJSP = CONFIG.PAGE_VIEW_BANK_ACCOUNT_1;
            MasaryManager.logger.info(action + " " + agentID + " IP " + request.getRemoteAddr());
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            if (ex.getMessage().contains("ORA-20139")) {
                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                nextJSP = CONFIG.PAGE_ERRPR_SSO;
            } else {
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        }

        return nextJSP;
    }

    private String treeList(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        List transAg = new ArrayList();
        request.getSession().removeAttribute("include");
        request.getSession().removeAttribute("transFromAg");
        List<Object[]> customers = (List<Object[]>) session.getAttribute("treeList");
        String customerID = request.getParameter(CONFIG.PARAM_CUSTOMER_ID);
        String LogId = (String) request.getSession().getAttribute("custIdForAccSession");
        String trxType = request.getParameter(CONFIG.PARAM_TRANSACTION_TYPE);
        ArrayList<String> services = new ArrayList<String>();
        String dateFrom = request.getParameter("From");
        String dateTo = request.getParameter("To");
        Enumeration parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String serviceId = (String) parameters.nextElement();
            String serviceValue = request.getParameter(serviceId);
            if ("on".equals(serviceValue) && serviceId.matches("[-+]?\\d+(\\.\\d+)?")) {
                services.add(serviceId);
            }
        }
        boolean inTree = false;
        for (Object[] customer : customers) {
            if (customer[0].toString().equals(customerID)) {
                inTree = true;
                break;
            }
        }
        if (!services.isEmpty() && inTree) {
            try {

                boolean isValidReprintVoucher = false;
                String custId = null;
                if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                    custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
                } else {
                    custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                }
                if (custId.equals(customerID)) {
                    isValidReprintVoucher = true;
                }

                transAg = MasaryManager.getInstance().getTransactionsList(customerID, dateFrom, dateTo, services, trxType, LogId);
                session.setAttribute("sumOfTrans", MasaryManager.getInstance().getSumOfTrx());
                session.setAttribute("custIdForAccSession", customerID);
                session.setAttribute("transFromAg", transAg);
                session.setAttribute("include", "transFromAg");
                session.setAttribute("isValidReprintVoucher", isValidReprintVoucher);

            } catch (Exception ex) {
                if (ex.getMessage().contains("ORA-20139")) {
                    session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                    nextJSP = CONFIG.PAGE_ERRPR_SSO;
                } else {
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }

            }
        }
        nextJSP = CONFIG.PAGE_VIEW_TREE_TRANSACTION;
        MasaryManager.logger.info(action + " " + customerID + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String assignServiceBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String amount = request.getParameter(CONFIG.AMOUNT);
        String serviceId = (String) session.getAttribute(CONFIG.PARAM_SERVICE_ID);
        try {
            if (Double.parseDouble(amount) < 0) {
                throw new Exception(CONFIG.getBalanceError(session));
            }
            MasaryManager.getInstance().assignServiceBalance(id, serviceId, amount, 0);
            nextJSP
                    = CONFIG.PAGE_VIEW_AGENT_SERVICES;
            if (isFromMobile(session)) {
                nextJSP = balanceMobile();
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute(
                    "ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        MasaryManager.logger.info(action + " " + serviceId + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private boolean isFromMobile(HttpSession session) {
        return CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE));
    }

    private String manageAgent(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String token = session.getAttribute("Token").toString();
        Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
        String action = requestParameters.get(CONFIG.PARAM_ACTION);
        String password = null;
        String confirmPassword = null;
        password = requestParameters.get(CONFIG.PARAM_PASSWORD);
        confirmPassword = requestParameters.get(CONFIG.PARAM_CONFIRM_PASSWORD);
        String oldPassword = requestParameters.get(CONFIG.PARAM_OLD_PASSWORD);

        System.out.println("Pawword UTF-8" + password);
        System.out.println("Pawword UTF-8" + confirmPassword);
        System.out.println("Old Pawword UTF-8" + oldPassword);

        String id = null;
        if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString().equals("-1")) {
            id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        } else {
            id = (String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
        }
        String sQ = requestParameters.get(CONFIG.PARAM_QUESTION);
        String answer = requestParameters.get(CONFIG.PARAM_ANSWER);
        String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
        String sessionStr = requestParameters.get(CONFIG.PARAM_SEESSIONTIME);
        if (sessionStr == null || sessionStr.equals("")) {
            sessionStr = "4";
        }
        int sessionTimeOut = Integer.parseInt(sessionStr);
        MasaryManager.logger.info(action + " " + id + " IP " + request.getRemoteAddr());
        try {
            if ((sQ == null && answer != null) || (sQ != null && answer == null)) {
                throw new Exception(CONFIG.getConfirmationError(session));
            }
            if (oldPassword == null) {

                throw new Exception(CONFIG.getOldPasswordEmptyError(session));

            }

            if (password != null) {
                if (password.length() < 4 || password.length() > 20) {
                    throw new Exception(CONFIG.getPasswordLengthError(session));
                }
                if (!password.equals(confirmPassword)) {
                    throw new Exception(CONFIG.getPasswordConfirmationError(session));
                }
            }
            if (oldPassword != null) {
                if ((oldPassword.length() < 4 || oldPassword.length() > 20)) {
                    throw new Exception(CONFIG.getPasswordLengthError(session));
                }
            }
            System.out.println("password before" + password);

            String loginAuthentication = SystemSettingsUtil.getInstance().loadProperty("authentication.service.migration.status");

//			int result = MasaryManager.getInstance().updateUser(id, password, sQ, answer, sessionTimeOut, oldPassword);
            UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
            updateProfileRequest.setCustomerID(id);
            updateProfileRequest.setSecurityQuestion(sQ);
            updateProfileRequest.setAnswer(answer);
            updateProfileRequest.setNewPassword(password);
            updateProfileRequest.setOldPassword(oldPassword);
            updateProfileRequest.setSessionTime(sessionTimeOut);

            UpdateProfileResponse result = new UpdateProfileResponse();

            if (loginAuthentication.trim().equalsIgnoreCase("on")) {
                UpdateProfileClient updateProfileClient = new UpdateProfileClient();
                result = updateProfileClient.updateProfile(updateProfileRequest, token);
                MasaryManager.logger.info("Data returned from backend :" + result.getGlobalTrxId());
                switch (result.getStatusCode()) {
                    case 1:
                        MasaryManager.logger.info("Customer " + id + "Updated Successfully ");
                        break;
                    case -30:
                        throw new Exception(CONFIG.getOldPasswordDoesnotMatchError(session));
                    case -20:
                        throw new Exception(CONFIG.getOldPasswordEmptyError(session));
                    default:
                        MasaryManager.logger.info(result);
                        break;
                }
            } else {

                int result2 = MasaryManager.getInstance().updateUser(id, password, sQ, answer, sessionTimeOut, oldPassword);
                if (result2 == 1) {
                    MasaryManager.logger.info("Customer " + id + "Updated Successfully ");
                } else if (result2 == -30) {
                    throw new Exception(CONFIG.getOldPasswordDoesnotMatchError(session));
                } else if (result2 == -20) {
                    throw new Exception(CONFIG.getOldPasswordEmptyError(session));
                } else {
                    MasaryManager.logger.info(result2);

                }

            }

            // } catch (SQLException e) {
            // MasaryManager.logger.error(e);
            // request.setAttribute("ErrorMessage", e.getMessage());
            // nextJSP = CONFIG.PAGE_ERRPR;
            ///////////////////////////
            // Get info //////
            try {
                getinfoDTO cc = new getinfoDTO();
                cc = GetAccountInfo.GetAccount(id);
                /// NewSystem update/////////
                UpdateAccount account = new UpdateAccount();
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setAccountCategory(cc.getAccountCategoryId());
                accountDTO.setUserName(cc.getAccountUserName());
                accountDTO.setAccountId(id);
                accountDTO.setAccountStatus(cc.getAccountStatus());
                accountDTO.setAccountType(cc.getAccountType());
                accountDTO.setRatePlanId(cc.getRatePlanId());
                accountDTO.setAccountName(cc.getAccountName());
                accountDTO.setAddress(cc.getAddress());
                accountDTO.setAlternativeMobile(cc.getAlternativeMobile());
                accountDTO.setBirthDate(cc.getBirthDate());
                accountDTO.setCountry(cc.getCountry());
                accountDTO.setEmail(cc.getEmail());// else types
                accountDTO.setGovernerate(cc.getGovernrate());/// sent fromm db
                accountDTO.setLandline(cc.getLandline());
                accountDTO.setNationalId(cc.getNationalId());
                accountDTO.setmobileNumber(cc.getMobileNumber());
                accountDTO.setPostalNumber(cc.getPostalNumber());
                accountDTO.setQuestion(sQ);
                accountDTO.setAnswer(answer);
                accountDTO.setPassword(password);
                accountDTO.setOldPassword(oldPassword);
                accountDTO.setSessionTimeOut(sessionTimeOut);

                // accountDTO.setUserName(String.valueOf(info.getCustomerID())); //update
                Gson gson = new Gson();
                String requestjson = gson.toJson(accountDTO);
                account.UpdateAccount(id, requestjson);
            } catch (Exception e) {
                MasaryManager.logger.error("EXCEPTION IN UPDATE ACCOUNT IN NEW SYSTEM ");
            }

            nextJSP = role + ".jsp";
            // } catch (SQLException e) {
            // MasaryManager.logger.error(e);
            // request.setAttribute("ErrorMessage", e.getMessage());
            // nextJSP = CONFIG.PAGE_ERRPR;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            if (ex.getMessage().contains("ORA-20139")) {
                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                nextJSP = CONFIG.PAGE_ERRPR_SSO;
            } else if (ex.getMessage().contains("ORA-2050")) {
                String msg = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1,
                        ex.getMessage().indexOf("ORA-06512:"));
                session.setAttribute("ErrorMessage", msg);
                nextJSP = CONFIG.PAGE_ERRPR;
            } else {
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        }
        return nextJSP;
    }

    private String assignAgentBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String name = null, agentId = null;
        nextJSP = "";
        MasaryManager.logger.info(action + " " + agentId + " IP " + request.getRemoteAddr());
        try {
            while (e.hasMoreElements()) {
                agentId = (String) e.nextElement();
//                try {
                name = request.getParameter(agentId);
//                    byte[] bytes = request.getParameter(agentId).getBytes("ISO-8859-1");
//                    name = new String(bytes, "UTF-8");
//                    name = request.getParameter(agentId;
//                } catch (UnsupportedEncodingException ex) {
//                    MasaryManager.logger.error(ex);
//                }
                if (name.equals(CONFIG.getAssign(session))) {
                    break;
                }
                if (name.equals(CONFIG.getManageAgent(session))) {
                    nextJSP = CONFIG.MANAGE_ROLES;
                    session.setAttribute(CONFIG.PARAM_AGENT_ID, agentId);
                    break;
                }
                if (name.equals(CONFIG.getDeactivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(agentId, false);
                    nextJSP = CONFIG.PAGE_VIEW_AGENTS;
                    break;
                }
                if (name.equals(CONFIG.getActivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(agentId, true);
                    nextJSP = CONFIG.PAGE_VIEW_AGENTS;
                    break;
                }
            }
        } catch (SQLException ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        if (!(CONFIG.PAGE_VIEW_AGENTS.equals(nextJSP) || CONFIG.MANAGE_ROLES.equals(nextJSP))) {
            String balance = request.getParameter(CONFIG.PARAM_BALANCE);
            String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
            if (balance == null) {
                session.setAttribute(CONFIG.PARAM_AGENT_ID, agentId);
                nextJSP = CONFIG.PAGE_ASSIGN_AGENTS;
            } else {
                agentId = (String) session.getAttribute(CONFIG.PARAM_AGENT_ID);
                String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                try {
                    String transId = String.valueOf(MasaryManager.getInstance().addBalance(agentId, id, balance));
                    if (serviceId != null) {
                        MasaryManager.getInstance().updateTransType(transId, serviceId);
                        MasaryManager.getInstance().assignServiceBalance(agentId, serviceId, balance, (id.equals("1") ? 1 : 0));
                    }
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                    request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                    nextJSP = CONFIG.PAGE_VIEW_TRANSACTION;
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex);
                    session.setAttribute("ErrorMessage",
                            ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
        }
        return nextJSP;
    }

    private void AgentTo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String CUSTOMER_ID = request.getParameter("CUSTOMER_ID");
            CustomerDTO customer = MasaryManager.getInstance().getCustomerInfo(CUSTOMER_ID);
            out.println(customer.getCustomerName(session));
        } catch (Exception e) {
            MasaryManager.logger.error(e.getMessage());
            out.print("");
        } finally {
            out.close();
        }
    }

    private void addTransferFees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String CUSTOMER_ID = request.getParameter("CUSTOMER_ID");
            String TO_CUSTOMER = request.getParameter("TO_CUSTOMER");
            String AMOUNT = request.getParameter("AMOUNT");
            long fromUser = Long.parseLong(CUSTOMER_ID);
            long toUser = Long.parseLong(TO_CUSTOMER);
            double amount = Long.parseLong(AMOUNT);
            String feesValue
                    = MasaryManager.getInstance().callMASARY_TRANSFER_FEES(fromUser, toUser, amount);
            out.println(feesValue);
        } catch (Exception e) {
            MasaryManager.logger.error(e);
            MasaryManager.logger.error(e.getStackTrace());

        } finally {
            out.close();
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
        processGetRequest(request, response);
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
        HttpServletRequestProxy httpServletRequestProxy = new HttpServletRequestProxy();
        httpServletRequestProxy.setRequest(request);

        processPostRequest(httpServletRequestProxy, response);
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
