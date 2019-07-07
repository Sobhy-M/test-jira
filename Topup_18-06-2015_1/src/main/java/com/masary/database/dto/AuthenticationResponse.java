/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Melad
 */
public class AuthenticationResponse {

    private String id;
    private String role;
    private String empID;
    private String response;
    private boolean valid;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public AuthenticationResponse(String id, String role, String empID) {
        this.id = id;
        this.role = role;
        this.empID = empID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmpID(String EmpID) {
        this.empID = EmpID;
    }

    public String getEmpID() {
        return empID;
    }

    @Override
    public String toString()
    {
        return "AuthenticationResponse{" + "id=" + id + ", role=" + role + ", empID=" + empID + ", response=" + response + ", valid=" + valid + "}";
    }
    
    
}
