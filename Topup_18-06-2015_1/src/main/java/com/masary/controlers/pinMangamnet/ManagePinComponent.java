package com.masary.controlers.pinMangamnet;

import org.apache.http.impl.client.HttpClients;
import com.masary.database.dto.AddNewPinRequestDTO;
import com.masary.integration.LockBalanceClient;
import com.masary.integration.dto.ChangePinRequestDTO;
import com.masary.integration.dto.GetPinStatusRepresentation;

public class ManagePinComponent {

	public GetPinStatusRepresentation getPinStatus(String lang, String token, String ip) throws Exception {

		GetPinStatusRepresentation getPinStatusRepresentation = new GetPinStatusRepresentation();

		LockBalanceClient balanceClient = new LockBalanceClient(HttpClients.createDefault());

		getPinStatusRepresentation = balanceClient.getPinStatus(lang, token, ip);

		return getPinStatusRepresentation;
	}

	public void addNewPin(AddNewPinRequestDTO addNewPinRequestDTO) throws Exception {

		LockBalanceClient balanceClient = new LockBalanceClient(HttpClients.createDefault());

		balanceClient.addNewPin(addNewPinRequestDTO);
	}

	public void changePinStatus(ChangePinRequestDTO pinRequestDTO) throws Exception {
		LockBalanceClient balanceClient = new LockBalanceClient(HttpClients.createDefault());

		balanceClient.changePinStatus(pinRequestDTO);
	}
}
