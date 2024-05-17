package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorLog {
    private static final String LOG_FILE = "src/main/java/logging/logs.txt";

    public static void log(String message){
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String currentDateTime = formatoData.format(new Date());

            PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true));

            out.println(currentDateTime + " - " + message);

            out.close();
        } catch (IOException e){
            System.out.println("Houve um erro ao registrar o log!");
            e.printStackTrace();
        }
    }
}
