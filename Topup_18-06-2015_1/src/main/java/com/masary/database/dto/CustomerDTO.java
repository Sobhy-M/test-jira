/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Represents customer
 *
 * @author Melad
 */
public class CustomerDTO {

    private int customerID;
    private int parentId;
    private double currentBalance;
    private String customerName;
    private String customerArabicName;
    private String customerQuestion;
    private String customerAnswer;
    private String customerPhone;
    private List<String> roles;
    private List<CustomerDTO> customerDTOs;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<CustomerDTO> getCustomerDTOs() {
        return customerDTOs;
    }

    public void setcCustomerDTOs(List<CustomerDTO> customerDTOsList) {
        this.customerDTOs = customerDTOsList;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    int customerBalance;

    public int getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(int customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getCustomerArabicName();
        }

        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerQuestion() {
        return customerQuestion;
    }

    public void setCustomerQuestion(String customerQuestion) {
        this.customerQuestion = customerQuestion;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCustomerArabicName() {
        if (customerArabicName == null) {
            return customerName;
        }
        return customerArabicName;
    }

    public void setCustomerArabicName(String customerArabicName) {
        this.customerArabicName = customerArabicName;
    }
}
