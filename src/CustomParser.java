//from html.parser import HTMLParser
//import urllib.request

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class CustomParser {
    public static void parseAndRewrite(String filepath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line=br.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            System.err.println("Couldn't read file: "+e);
        }
        String content = contentBuilder.toString();

        try (PrintWriter pw = new PrintWriter(filepath)) {

            Document doc = Jsoup.parse(content);
            Elements newsHeadlines = doc.select("div.vmede-generator");
            for (Element headline : newsHeadlines) {
                for (Element el : headline.getAllElements()) {
                    pw.print(el.text());
                }
            }

        } catch (IOException e) {
            System.err.println("Couldn't write file: "+e);
        }
    }
}

//class CustomParser(HTMLParser):
//
//    writeDataToFile = False
//    file = None
//
//    def __init__(self, file):
//        super().__init__()
//        self.file = file
//
//    def handle_starttag(self, tag, attrs):
//        result = None
//        for attr_name, attr_value in attrs:
//            if attr_name == "class":
//                result = attr_value
//        if tag == "div" and result == "vmede-generator":
//            self.writeDataToFile=True
//
//    def handle_endtag(self, tag):
//        if tag == "div" and self.writeDataToFile:
//            self.writeDataToFile=False
//
//    def handle_data(self, data):
//        if self.writeDataToFile:
//            file.write(data)
