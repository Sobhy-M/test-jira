package com.masary.database.dto;

import com.masary.common.CONFIG;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Represents Customer (Masary user) data
 *
 * @author Melad
 */
public class AgentDTO {

    private String pin, name, phone, arabicName;
    private double balance;
    private boolean isActive;
    private String isAutoAllocate;
    private boolean canAddEmployee;
    private ArrayList<String> roles;
    private boolean isSupervisor;
    private int blackListReason;
    private int groupId;
    private String parentID;
    private String ruleID;
    private String warning;
    private String notifications;
    private int sso;

    public int getSso() {
        return sso;
    }

    public void setSso(int sso) {
        this.sso = sso;
    }
    
    
    

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getRuleID() {
        return ruleID;
    }

    public void setRuleID(String ruleID) {
        this.ruleID = ruleID;
    }

    public String getArabicName() {
        if (arabicName == null) {
            return name;
        }
        return arabicName;
    }

    public void addRole(String roleName) {
        roles.add(roleName);
    }

    public AgentDTO() {
        // roles = new ArrayList<String>();
        services = new ArrayList();
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPhone() {
        return phone;
    }

    public boolean canAddEmployee() {
        return canAddEmployee;
    }

    public void setCanAddEmployee(boolean canAddEmployee) {
        this.canAddEmployee = canAddEmployee;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName(HttpSession session) {
        if (session == null) {
            return name;
        }
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getArabicName();
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean hasRole(String roleName) {
        for (int i = roles.size() - 1; i >= 0; i--) {
            if (roles.get(i).equals(roleName)) {
                return true;
            }
        }
        return false;
    }
    private List<CustomerServiceDTO> services;

    /**
     * Get the value of services
     *
     * @return the value of services
     */
    public List getServices() {
        return services;
    }

    /**
     * Set the value of services
     *
     * @param services new value of services
     */
    public void setServices(List services) {
        this.services = services;
    }

    public void addService(CustomerServiceDTO service) {
        this.services.add(service);
    }

    public Double getServiceBalance(int serviceID) {
        if (isAutoAllocate.equals("N")) {
            serviceID = 1;
        } else if (isAutoAllocate.equals("Y")) {
            for (CustomerServiceDTO service : services) {
                if (service.getServiceID() == serviceID) {
                    if (service.allowAutoAllocat() == 1) {
                        serviceID = 1;
                    }
                }
            }

        } else {
        }

        for (CustomerServiceDTO service : services) {
            if (service.getServiceID() == serviceID) {
                return service.getServiceBalance();
            }
        }
        return 0.0;
    }

    public String getServiceName(int serviceID, HttpSession session) {
        for (CustomerServiceDTO service : services) {
            if (service.getServiceID() == serviceID) {
                return service.getServiceName(session);
            }
        }
        return null;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;

    }

    public String isAutoAllocate() {
        return isAutoAllocate;
    }

    public void setAutoAllocate(String isAutoAllocate) {
        this.isAutoAllocate = isAutoAllocate;
    }

    public boolean isSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public void setIsSupervisor(int repsCount) {
        this.isSupervisor = repsCount > 0;
    }

    public int getBlackListReason() {
        return blackListReason;
    }

    public void setBlackListReason(int blackListReason) {
        this.blackListReason = blackListReason;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
