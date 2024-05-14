import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorLog {
    private static final String LOG_FILE = "application.txt";

    public static void log(String message){
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = formatoData.format(new Date());

            PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true));

            out.println(currentDateTime + " hello");

            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
