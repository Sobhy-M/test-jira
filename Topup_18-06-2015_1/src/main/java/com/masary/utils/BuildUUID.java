/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

import java.sql.Timestamp;
import java.util.UUID;


/**
 *
 * @author Masary
 */
public class BuildUUID {
      public String extTrxId ; 
    public String CreateUUID ()
    {
        String uuid = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
  
        String CurrentTime =timestamp.toGMTString();
            
        this.extTrxId= uuid + CurrentTime ;
        
        return extTrxId ;
    }
}
