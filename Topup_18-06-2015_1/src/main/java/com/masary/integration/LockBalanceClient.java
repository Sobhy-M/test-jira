package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.controlers.pinMangamnet.PinManagmentProperties;
import com.masary.database.dto.AddNewPinRequestDTO;
import com.masary.database.manager.MasaryManager;
import com.masary.exceptions.LockBalanceCallerException;
import com.masary.integration.dto.AddNewPinDTO;
import com.masary.integration.dto.ChangePinRequestDTO;
import com.masary.integration.dto.ChangePinStatusDTO;
import com.masary.integration.dto.GetPinStatusRepresentation;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;

public class LockBalanceClient {

	private CloseableHttpClient httpClient;

	public LockBalanceClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public void changePinStatus(ChangePinRequestDTO pinRequestDTO) throws Exception {

		String etisalatTopupUrl = SystemSettingsUtil.getInstance().loadProperty("lock.balance.pin.change");
		CloseableHttpClient httpclient = this.httpClient;
		HttpPost httpPost = new HttpPost(etisalatTopupUrl);
		Gson gson = new Gson();

		ChangePinStatusDTO changePinStatusDTO = new ChangePinStatusDTO(pinRequestDTO.getBinCode(),
				pinRequestDTO.getNewStatus());

		String urlParameters = gson.toJson(changePinStatusDTO, ChangePinStatusDTO.class);

		StringEntity params = new StringEntity(urlParameters);
		httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Authorization", pinRequestDTO.getToken());
		httpPost.addHeader("ip", pinRequestDTO.getIp());
		httpPost.addHeader("deviceType", "Web");
		httpPost.setEntity(params);

		try {

			CloseableHttpResponse response = httpclient.execute(httpPost);

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			MasaryManager.logger.info(response.getStatusLine());
			MasaryManager.logger.info("JSON: " + json);

			if (response.getStatusLine().getStatusCode() == 200) {
				MasaryManager.logger.info("Success to change pin status");

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(pinRequestDTO.getLang(),
						standardHttpJsonResponseDTO.getMessage());
				throw new LockBalanceCallerException(errorMessage);

			}

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
			MasaryManager.logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}
	}

	public GetPinStatusRepresentation getPinStatus(String lang, String token, String ip) throws Exception {

		String url = SystemSettingsUtil.getInstance().loadProperty("lock.balance.pin.status");

		CloseableHttpClient httpclient = this.httpClient;

		HttpGet httpGet = new HttpGet(url);
		Gson gson = new Gson();

		GetPinStatusRepresentation representation = new GetPinStatusRepresentation();

		try {
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("deviceType", "Web");

			CloseableHttpResponse response = httpclient.execute(httpGet);

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			MasaryManager.logger.info(response.getStatusLine());
			MasaryManager.logger.info("JSON: " + json);

			if (response.getStatusLine().toString().contains("200")) {
				representation = gson.fromJson(json, GetPinStatusRepresentation.class);
				MasaryManager.logger.info("Success transaction etisalat integration  " + representation);

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new LockBalanceCallerException(errorMessage);

			}

			return representation;

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
			MasaryManager.logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	public void addNewPin(AddNewPinRequestDTO requestDTO) throws Exception {

		String etisalatTopupUrl = SystemSettingsUtil.getInstance().loadProperty("lock.balance.pin.create");
		CloseableHttpClient httpclient = this.httpClient;
		HttpPost httpPost = new HttpPost(etisalatTopupUrl);
		Gson gson = new Gson();

		AddNewPinDTO changePinStatusDTO = new AddNewPinDTO(requestDTO.getPinCode(),
				requestDTO.getPinCodeConfirmation());

		String urlParameters = gson.toJson(changePinStatusDTO, AddNewPinDTO.class);

		StringEntity params = new StringEntity(urlParameters);
		httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Authorization", requestDTO.getToken());
		httpPost.addHeader("ip", requestDTO.getIp());
		httpPost.addHeader("deviceType", "Web");
		httpPost.setEntity(params);

		try {

			CloseableHttpResponse response = httpclient.execute(httpPost);

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			MasaryManager.logger.info(response.getStatusLine());
			MasaryManager.logger.info("JSON: " + json);

			if (response.getStatusLine().getStatusCode() == 200) {
				MasaryManager.logger.info("Success to add new pin ");

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(requestDTO.getLang(), standardHttpJsonResponseDTO.getMessage());
				throw new LockBalanceCallerException(errorMessage);

			}

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
			MasaryManager.logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	private String getErrorMessage(String lang, String reason) {

		String errorMessage = "";

		if (reason.equals("16")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_16_Ar
					: PinManagmentProperties.errorCode_16_En;
		} else if (reason.equals("41002")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41002_Ar
					: PinManagmentProperties.errorCode_41002_En;

		} else if (reason.equals("41003")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41003_Ar
					: PinManagmentProperties.errorCode_41003_En;

		} else if (reason.equals("41004")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41004_Ar
					: PinManagmentProperties.errorCode_41004_En;

		} else if (reason.equals("41005")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41005_Ar
					: PinManagmentProperties.errorCode_41005_En;

		} else if (reason.equals("41006")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41006_Ar
					: PinManagmentProperties.errorCode_41006_En;

		} else if (reason.equals("41007")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41007_Ar
					: PinManagmentProperties.errorCode_41007_En;

		} else if (reason.equals("41008")) {
			errorMessage = lang.equals("ar") ? PinManagmentProperties.errorCode_41008_Ar
					: PinManagmentProperties.errorCode_41008_En;

		} else {
			errorMessage = lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError;

		}

		return errorMessage += (" [E " + reason + " ] ");

	}

}
