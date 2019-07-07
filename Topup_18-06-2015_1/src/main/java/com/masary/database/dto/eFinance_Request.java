/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class eFinance_Request {

    int Request_Type;
    String Sender_Id;
    String Message;

    public int getRequest_Type() {
        return Request_Type;
    }

    public void setRequest_Type(int Request_Type) {
        this.Request_Type = Request_Type;
    }

    public String getSender_Id() {
        return Sender_Id;
    }

    public void setSender_Id(String Sender_Id) {
        this.Sender_Id = Sender_Id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
