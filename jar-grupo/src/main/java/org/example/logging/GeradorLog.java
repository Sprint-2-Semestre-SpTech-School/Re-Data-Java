package org.example.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorLog {
    private static final String LOG_FILE = "src/main/java/org/example/logging/logs.txt";

    public static void log(String message){
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String currentDateTime = formatoData.format(new Date());
            String userName = System.getProperty("user.name");

            PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true));

            out.println(currentDateTime + " - " + userName + " - " + message);

            out.close();
        } catch (IOException e){
            System.out.println("Houve um erro ao registrar o log!");
            e.printStackTrace();
        }
    }
}
