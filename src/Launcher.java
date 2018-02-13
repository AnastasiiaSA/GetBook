import java.io.*;
import java.net.URL;

public class Launcher {

    public static void main(String[] args) {
        final String URL_START = "http://vmede.org/sait/?page=";
        for (int x = 1; x < 10; x++) {
            final String URL_END = "&id=Anatomija_sapin_"+x+"&menu=Anatomija_sapin_2007/";
            final String fileName = "part_"+x+".txt";

            try (PrintWriter pw = new PrintWriter(fileName)) {
                System.out.print("Downloading pages.. ");
                for (int a = 1; a < 25; a++) {
                    System.out.print(a+ (a < 24? "," : ""));
                    URL url = new URL(URL_START+a+URL_END);
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            url.openStream(), "UTF-8"));
                    String line;
                    while ((line = in.readLine()) != null) {
                        pw.println(line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Couldn't save to file: " + e);
            }

            System.out.print(" | Reformatting");
            CustomParser.parseAndRewrite(fileName);
            System.out.println(" | Done");
        }
    }
}
