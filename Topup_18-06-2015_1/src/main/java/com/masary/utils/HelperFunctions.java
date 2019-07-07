/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

/**
 *
 * @author y
*/
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelperFunctions {

    public String stringFormat(String string, int digitNumbers, char separator) {
        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i < string.length(); i++) {
            if ((i % digitNumbers) == 0) {
                newString.append(separator);
            }
            newString.append(string.charAt(i));
        }
        return newString.toString();
    }
}
