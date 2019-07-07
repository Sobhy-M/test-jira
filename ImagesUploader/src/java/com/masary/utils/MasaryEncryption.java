/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

//import java.util.Base64;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

/**
 *
 * @author hammad
 */
public class MasaryEncryption {

    public static String encrypt(String data) throws Exception {
//        byte[] bytesEncoded = Base64.getEncoder().encode(data.getBytes());
//        return new String(bytesEncoded);
        String bytesEncoded = new BASE64Encoder().encode(data.getBytes());
        return bytesEncoded;

    }

    public static String decrypt(String encryptedData) throws Exception {
//        byte[] bytesDecoded = Base64.getDecoder().decode(encryptedData);
//        return new String(bytesDecoded);
        
        byte[] bytesDecoded = new BASE64Decoder().decodeBuffer(encryptedData);
        return new String(bytesDecoded);
    }

}
