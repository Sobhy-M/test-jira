/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Masary_signon_profile {
int SIGNON_PROFILE_ID;
String SENDER;
String RECIEVER;
String VERSION;

    public String getRECIEVER() {
        return RECIEVER;
    }

    public void setRECIEVER(String RECIEVER) {
        this.RECIEVER = RECIEVER;
    }

    public String getSENDER() {
        return SENDER;
    }

    public void setSENDER(String SENDER) {
        this.SENDER = SENDER;
    }

    public int getSIGNON_PROFILE_ID() {
        return SIGNON_PROFILE_ID;
    }

    public void setSIGNON_PROFILE_ID(int SIGNON_PROFILE_ID) {
        this.SIGNON_PROFILE_ID = SIGNON_PROFILE_ID;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

}
