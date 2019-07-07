/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hammad
 */
public class EmployeeDTO {

    String employeeID;
    String parentID;
    String employeeName;
    String employeeArabicName;
    private boolean showMyBalance;
    private boolean showTransfer;
    String employeePhone;
    private double allowedBalance;
    private double curBalance;

    public double getCurBalance() {
        return curBalance;
    }

    public void setCurBalance(double curBalance) {
        this.curBalance = curBalance;
    }


    public double getAllowedBalance() {
        return allowedBalance;
    }

    public void setAllowedBalance(double allowedBalance) {
        this.allowedBalance = allowedBalance;
    }

    public boolean isShowMyBalance() {
        return showMyBalance;
    }

    public void setShowMyBalance(boolean showMyBalance) {
        this.showMyBalance = showMyBalance;
    }

    public boolean isShowTransfer() {
        return showTransfer;
    }

    public void setShowTransfer(boolean showTransfer) {
        this.showTransfer = showTransfer;
    }
 

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

//    public String getEmployeeAnswer() {
//        return employeeAnswer;
//    }
//
//    public void setEmployeeAnswer(String employeeAnswer) {
//        this.employeeAnswer = employeeAnswer;
//    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    
    public String getEmployeeName(HttpSession session) {
           if (session.getAttribute(CONFIG.lang).equals("ar")) {
               return getEmployeeArabicName();
           }

        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
//
//    public String getEmployeeQuestion() {
//        return employeeQuestion;
//    }
//
//    public void setEmployeeQuestion(String employeeQuestion) {
//        this.employeeQuestion = employeeQuestion;
//    }

    public String getParentId() {
        return parentID;
    }

    public void setParentId(String parentId) {
        this.parentID = parentId;
    }

    public String getEmployeeArabicName() {
        if (employeeArabicName == null) {
            return employeeName;
        }
        return employeeArabicName;
    }

    public void setEmployeeArabicName(String employeeArabicName) {
        this.employeeArabicName = employeeArabicName;
    }
}
