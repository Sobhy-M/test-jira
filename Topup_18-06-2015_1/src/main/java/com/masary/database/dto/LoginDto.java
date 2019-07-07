/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author Ehab
 */
public class LoginDto {

    AgentDTO agent;
    EmployeeDTO emp;
    CustomerServiceDTO service;
    String[] balance;
    AuthenticationResponse response;
    List<Masary_Biller> biller;
    List<CustomerServiceDTO> billerMenu;
    List<Masary_Biller> billerBTC;
    List<Masary_Bill_Type> billerBTCMenu;
    List<AgentDTO> supervisorRep;
    String menuXML;
    int SSo;
    String globalTrxId;
    

    public String getGlobalTrxId() {
		return globalTrxId;
	}

	public void setGlobalTrxId(String globalTrxId) {
		this.globalTrxId = globalTrxId;
	}

	public int getSSo() {
        return SSo;
    }

    public void setSSo(int SSo) {
        this.SSo = SSo;
    }
    
    
   

  
    

    public String getMenuXML() {
        return menuXML;
    }

    public void setMenuXML(String menuXML) {
        this.menuXML = menuXML;
    }

    public List<AgentDTO> getSupervisorRep() {
        return supervisorRep;
    }

    public void setSupervisorRep(List<AgentDTO> supervisorRep) {
        this.supervisorRep = supervisorRep;
    }

    public List<Masary_Bill_Type> getBillerBTCMenu() {
        return billerBTCMenu;
    }

    public void setBillerBTCMenu(List<Masary_Bill_Type> billerBTCMenu) {
        this.billerBTCMenu = billerBTCMenu;
    }

    public List<CustomerServiceDTO> getBillerMenu() {
        return billerMenu;
    }

    public void setBillerMenu(List<CustomerServiceDTO> billerMenu) {
        this.billerMenu = billerMenu;
    }

    public List<Masary_Biller> getBillerBTC() {
        return billerBTC;
    }

    public void setBillerBTC(List<Masary_Biller> billerBTC) {
        this.billerBTC = billerBTC;
    }

    public List<Masary_Biller> getBiller() {
        return biller;
    }

    public void setBiller(List<Masary_Biller> biller) {
        this.biller = biller;
    }

    public AuthenticationResponse getResponse() {
        return response;
    }

    public void setResponse(AuthenticationResponse response) {
        this.response = response;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public String[] getBalance() {
        return balance;
    }

    public void setBalance(String[] balance) {
        this.balance = balance;
    }

    public EmployeeDTO getEmp() {
        return emp;
    }

    public void setEmp(EmployeeDTO emp) {
        this.emp = emp;
    }

    public CustomerServiceDTO getService() {
        return service;
    }

    public void setService(CustomerServiceDTO service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "LoginDto{" + "agent=" + agent + ", emp=" + emp + ", service=" + service + ", balance=" + balance + ", response=" + response + ", biller=" + biller + ", billerMenu=" + billerMenu + ", billerBTC=" + billerBTC + ", billerBTCMenu=" + billerBTCMenu + ", supervisorRep=" + supervisorRep + ", SSo=" + SSo + ",globalTrxId=" + globalTrxId+'}';
    }
    
}
