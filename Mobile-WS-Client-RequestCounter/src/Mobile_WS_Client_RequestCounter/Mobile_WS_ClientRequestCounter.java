/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_WS_Client_RequestCounter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 *
 * @author Ahmed Khaled.28-12-2017
 */
public class Mobile_WS_ClientRequestCounter {

    /**
     * @param args the command line arguments
     */
    private static final Map<String, Integer> services = new TreeMap<>(Comparator.naturalOrder());

    public static void main(String[] args) throws Exception {

        String mobile_WS_Client = "/Mobile-WS-Client/";
        String par1 = "par1=";
//        BufferedReader br = null;

        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            System.out.println("==============Mobile-WS-Client===============");
            lines.forEach(line -> {
                if (line.contains(mobile_WS_Client) && (line.contains(par1.toLowerCase()) || line.contains(par1.toUpperCase()))) {
                    String text = "par1=";
                    int idx = line.indexOf(text);
                    String result = line.substring(idx + text.length());
                    char[] chars = result.toCharArray();
                    int x = getServiceId(result, chars);
                    services.compute(result.substring(0, x + 1), (k, v) -> v == null ? 1 : v + 1);

                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        services.forEach((k, v) -> System.out.println(k + " -> " + v));
        Optional<Integer> r = services.values().stream().reduce((i, acc) -> i + acc);
        System.out.println("===============================");
        r.ifPresent(System.out::println);
        if (args.length == 2) {
            writeLogFile(services, r, args[1]);
        }

    }

    public static int getServiceId(String result, char[] chars) {
        int y = 0;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                y = i;
            } else {
                break;
            }
        }
        return y;
    }

    public static void writeLogFile(Map<String, Integer> map, Optional<Integer> optional, String path) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        try (Writer writer = Files.newBufferedWriter(Paths.get(path + "Mobile-WS-Client_Request_Counter." + currentDate + ".txt"))) {
            map.forEach((key, value) -> {
                try {
                    writer.write(key + " -> " + value + System.lineSeparator());
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            });
            writer.write("===============================" + System.lineSeparator());
            writer.write(optional.get().toString());
        } catch (UncheckedIOException ex) {
            throw ex.getCause();
        }

    }

}
