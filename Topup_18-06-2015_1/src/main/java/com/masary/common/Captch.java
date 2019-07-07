/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.common;

import com.masary.database.manager.MasaryManager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 *
 * @author user
 */
public class Captch {

    private Captch() {
    }

    /**
     * Generates a random alpha-numeric string of eight characters.
     *
     * @return random alpha-numeric string of eight characters.
     */
    public static String generateText() {
        return new StringTokenizer(UUID.randomUUID().toString(), "-").nextToken();
    }

    /**
     * Generates a PNG image of text 180 pixels wide, 40 pixels high with white
     * background.
     *
     * @param text expects string size eight (8) characters.
     * @return byte array that is a PNG image generated with text displayed.
     */
    public static byte[] generateImage(String text) {

        MasaryManager.logger.info("starting generate captcha image ....");

        try {
            int w = 180, h = 40;
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            MasaryManager.logger.info("buffering an image .... ");

            Graphics2D g = image.createGraphics();

            MasaryManager.logger.info("create 2D image ....");

            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g.setColor(Color.white);
            g.fillRect(0, 0, w, h);
            g.setFont(new Font("Serif", Font.PLAIN, 26));
            g.setColor(Color.blue);
            int start = 10;
            byte[] bytes = text.getBytes();

            Random random = new Random();
            for (int i = 0; i < bytes.length; i++) {
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.drawString(new String(new byte[]{bytes[i]}), start + (i * 20), (int) (Math.random() * 20 + 20));
            }
            g.setColor(Color.white);
            for (int i = 0; i < 8; i++) {
                g.drawOval((int) (Math.random() * 160), (int) (Math.random() * 10), 30, 30);
            }
            g.dispose();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            MasaryManager.logger.info("byte array to ouputStream ....");
            try {
                ImageIO.write(image, "png", bout);
                MasaryManager.logger.info("writing image with extenstion ....");
            } catch (Exception e) {
                MasaryManager.logger.error("Error while generating captcha image");
                throw new RuntimeException(e);
            }
            MasaryManager.logger.info("generating captcha image succeded");

            return bout.toByteArray();
        } catch (Exception e) {
            MasaryManager.logger.error("Error while generating captcha image");
        }
        return null;
    }
}
