/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

import com.masary.database.manager.MasaryManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;

/**
 *
 * @author omnya
 */
public class SendEmail {

//    public void sendVoucherEmail(String customerEmail, String fileName, String emailSubject, String emailBody1) {
//        String result;
//        String to = customerEmail;
//        String from = "oramadan@e-masary.com";
//        String host = "smtp.gmail.com";
//        final String username = "service@e-masary.com";
//        final String password = "MasaryServices@2014";
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.user", username);
//        properties.setProperty("mail.password", password);
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("charset","utf-8");
//        
//        Session mailSession = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        MimeMessage message = null;
//        try {
//            message = new MimeMessage(mailSession);
//            message.setContent(emailBody1,"text/html; charset=utf-8");
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(emailSubject,"utf-8");
////            System.out.println("emailSubject"+emailSubject);
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(emailBody1);
////            System.out.println("emailBody1"+emailBody1);
//            Multipart multipart = new MimeMultipart();
//            
//            multipart.addBodyPart(messageBodyPart);
//            message.setHeader("Content-Type","text/html; charset=utf-8");
//
//            //Handling multi-files attaching. The files should be separated by ";"
//            if (fileName.contains(";")) {
//                String[] allFiles = fileName.split(";");
//                for (int i = 0; i < allFiles.length; i++) {
//                    multipart.addBodyPart(attachFiles(allFiles[i]));
//                }
//            } else {
//                multipart.addBodyPart(attachFiles(fileName + ".xls"));
//            }
//
//            message.setContent(multipart);
//            Transport.send(message);
//            String title = "Send Email";
//            result = "Sent message successfully....";
////            File file = new File(filename);
////            if (file.delete()) {
////                System.out.println(file.getName() + " is deleted!");
////            } else {
////                System.out.println("Delete operation is failed.");
////            }
//        } catch (Exception mex) {
//            MasaryManager.logger.error(mex);
//            result = "Error: unable to send message....";
//        }
//    }
//
//    private BodyPart attachFiles(String fileName) {
//        BodyPart messageBodyPart = new MimeBodyPart();
//        try {
//            String filename = fileName;
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//        } catch (Exception ex) {
//            MasaryManager.logger.error(ex);
//        }
//        return messageBodyPart;
//    }
}
