/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import static com.masary.common.CONFIG.lang;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.ElectricityInquiryResponse;
import com.masary.integration.dto.ElectricityPaymentRequest;
import com.masary.integration.dto.ElectricityPaymentResponse;
import com.masary.integration.dto.InternetEgyptPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.net.ConnectException;
import javax.servlet.http.HttpServlet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mustafa
 */
public class ElectricityClient extends HttpServlet {

	public ElectricityInquiryResponse electricityInquiry(String lang, String accountNumber, String serviceID,
			String uuid, String token) throws Exception {
		String electricityInquiry = "";

		if (serviceID.equals("99019")) {
			electricityInquiry = SystemSettingsUtil.getInstance().loadProperty("service.alexElectricityInquiry.url");
		} else if (serviceID.equals("99022")) {
			electricityInquiry = SystemSettingsUtil.getInstance().loadProperty("service.beharaElectricityInquiry.url");
		} else if (serviceID.equals("99021")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.northCairoElectricityInquiry.url");
		} else if (serviceID.equals("99012")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.MiddleEgyptElectricityInquiry.url");
		} else if (serviceID.equals("99014")) {
			electricityInquiry = SystemSettingsUtil.getInstance().loadProperty("service.canalElectricityInquiry.url");
		} else if (serviceID.equals("99015")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.northDeltaElectricityInquiry.url");
		} else if (serviceID.equals("99018")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.southDeltaElectricityInquiry.url");
		} else if (serviceID.equals("99013")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.upperEgyptElectricityInquiry.url");
		} else if (serviceID.equals("99007")) {
			electricityInquiry = SystemSettingsUtil.getInstance()
					.loadProperty("service.southCairoElectricityInquiry.url");
		}
		electricityInquiry = electricityInquiry.replace("{accountNumber}", accountNumber);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		ElectricityInquiryResponse electricityInquiryRepresentation = new ElectricityInquiryResponse();

		try {
			HttpGet httpGet = new HttpGet(electricityInquiry);
			httpGet.addHeader("deviceType", "Web");
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("extTrxId", uuid);

			MasaryManager.logger.info("electricity  service inquiry request: " + electricityInquiry);
			MasaryManager.logger.info("electricity  service inquiry request uuid is: " + uuid);
			CloseableHttpResponse internetEgyptInquiryHttp = httpclient.execute(httpGet);

			String ResponseJson = EntityUtils.toString(internetEgyptInquiryHttp.getEntity(), "UTF-8");
			MasaryManager.logger.info("electricity  service response: " + ResponseJson);
			Gson gson = new Gson();
			if (internetEgyptInquiryHttp.getStatusLine().getStatusCode() == 200) {

				electricityInquiryRepresentation = gson.fromJson(ResponseJson, ElectricityInquiryResponse.class);

			} else {
				StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
				errorResponseDTO = gson.fromJson(ResponseJson, StandardHttpJsonResponseDTO.class);
				MasaryManager.logger
						.info("Failed to enquire from electricity  service " + errorResponseDTO.getMessage());

				if (errorResponseDTO.getStatus().equals("424") && errorResponseDTO.getMessage().equals("990006")) {
					throw new Exception("كود العميل غير موجود او لا يوجد فواتير مستحقه");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().equals("990002")) {
					throw new Exception(lang.equals("ar") ? "كود العميل غير موجود" : "invalid number");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().equals("990007")) {
					throw new Exception(lang.equals("ar") ? "كود العميل غير موجود او لا يوجد فواتير مستحقة"
							: "Bill Not Found For Customer");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().contains("9900")) {
					throw new Exception(
							lang.equals("ar") ? "الخدمة غير متاحة من قبل مزود الخدمة , الرجاء المحاولة في وقت لاحق"
									: "Service not available from provider");
				} else {
					throw new Exception(lang.equals("ar") ? "خطأ فى تنفيذ العملية" : "Error in operation");
				}
			}
		} catch (ConnectException ex) {
			MasaryManager.logger.error("Error during calling electricity inquiry service on new system: " + ex);
			throw (ex);
		} catch (Exception ex) {
			MasaryManager.logger.error("Error during calling electricity inquiry service on new system: " + ex);
			throw (ex);
		} finally {
			httpclient.close();
		}

		return electricityInquiryRepresentation;
	}

	public ElectricityPaymentResponse electricityPayment(String lang, ElectricityPaymentRequest electricityRequest,
			String uuid, String token) throws Exception {

		String ElectricityPayment = "";

		if (electricityRequest.getElectricityCompanyID().equals("99019")) {
			ElectricityPayment = SystemSettingsUtil.getInstance().loadProperty("service.alexElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99022")) {
			ElectricityPayment = SystemSettingsUtil.getInstance().loadProperty("service.beharaElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99021")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.northCairoElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99018")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.southDeltaElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99012")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.MiddleEgyptElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99014")) {
			ElectricityPayment = SystemSettingsUtil.getInstance().loadProperty("service.canalElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99015")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.northDeltaElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99013")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.upperEgyptElectricityPayment.url");
		} else if (electricityRequest.getElectricityCompanyID().equals("99007")) {
			ElectricityPayment = SystemSettingsUtil.getInstance()
					.loadProperty("service.southCairoElectricityPayment.url");
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();

		ElectricityPaymentResponse electricityPaymentResponse = new ElectricityPaymentResponse();

		try {
			HttpPost httpPost = new HttpPost(ElectricityPayment);
			httpPost.addHeader("deviceType", "Web");
			httpPost.addHeader("Authorization", token);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("extTrxId", uuid);

			Gson gson = new Gson();
			String urlParameters = gson.toJson(electricityRequest, ElectricityPaymentRequest.class);
			StringEntity params = new StringEntity(urlParameters);
			httpPost.setEntity(params);

			CloseableHttpResponse electricityPayResponseHttp = httpclient.execute(httpPost);

			String electricityPayResponseJson = EntityUtils.toString(electricityPayResponseHttp.getEntity(), "UTF-8");

			MasaryManager.logger.info("electricity payment response is : " + electricityPayResponseJson);
			
			MasaryManager.logger.info("electricity payment response uuid is : " + uuid);

			if (electricityPayResponseHttp.getStatusLine().getStatusCode() == 200) {

				electricityPaymentResponse = gson.fromJson(electricityPayResponseJson,
						ElectricityPaymentResponse.class);

			} else {
				StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
				errorResponseDTO = gson.fromJson(electricityPayResponseJson, StandardHttpJsonResponseDTO.class);
				MasaryManager.logger
						.info("Failed to enquire from electricity service " + errorResponseDTO.getMessage());

				if (errorResponseDTO.getStatus().equals("424") && errorResponseDTO.getMessage().equals("990006")) {
					throw new Exception("كود العميل غير موجود او لا يوجد فواتير مستحقه");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().equals("990002")) {
					throw new Exception(lang.equals("ar") ? "كود العميل غير موجود" : "invalid number");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().equals("990007")) {
					throw new Exception(lang.equals("ar") ? "كود العميل غير موجود او لا يوجد فواتير مستحقة"
							: "Bill Not Found For Customer");
				} else if (errorResponseDTO.getStatus().equals("424")
						&& errorResponseDTO.getMessage().contains("9900")) {
					throw new Exception(
							lang.equals("ar") ? "الخدمة غير متاحة من قبل مزود الخدمة , الرجاء المحاولة في وقت لاحق"
									: "Service not available from provider");
				} else {
					throw new Exception(lang.equals("ar") ? "خطأ فى تنفيذ العملية" : "Error in operation");
				}

			}

		} catch (ConnectException ex) {
			MasaryManager.logger.error("Error during calling electricity inquiry service on new system: " + ex);
			throw (ex);
		} catch (Exception ex) {
			MasaryManager.logger.error("Error during calling electricity  service on new system: " + ex, ex);
			throw (ex);
		} finally {
			httpclient.close();
		}

		return electricityPaymentResponse;
	}

}
