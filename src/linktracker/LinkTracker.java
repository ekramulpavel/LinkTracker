package linktracker;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class LinkTracker {
    public static void main(String[] args) throws URISyntaxException,IOException, BadLocationException 
    {
        
        Scanner scan = new Scanner(System.in);
        System.err.println("Ente the Urk link below...");
        String s = scan.nextLine();
        HTMLDocument doc = new HTMLDocument() {
            public HTMLEditorKit.ParserCallback getReader(int pos) {
                return new HTMLEditorKit.ParserCallback() {
                    public void handleText(char[] data, int pos) {
                        System.out.println(data);
                    }
                };
            }
        };
        // Define the url link for crawling website.....
        URL url = new URI(s).toURL();
        URLConnection conn = url.openConnection();
        Reader rd = new InputStreamReader(conn.getInputStream());
        //save the links in link.txt file 
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("link.txt"), "UTF-8");

        EditorKit kit = new HTMLEditorKit();
        kit.read(rd, doc, 0);
        try {
            Document docs = Jsoup.connect(s).get();

            Elements links = docs.select("a[href]");

            Elements elements = docs.select("*");
            System.out.println("Total Links :" + links.size());

            for (Element element : elements) {
                System.out.println(element.ownText());
            }
            //links are assinged into link and check them with write line by line 
            for (Element link : links) {
                String hrefUrl = link.attr("href");
                if (!"#".equals(hrefUrl) && !(hrefUrl).isEmpty()) {
                    System.out.println(hrefUrl);
                    writer.write(link.text() + " => \n" + hrefUrl + "\n");
                }
            }

        } catch (Exception e) {
        } finally {
            writer.close();
        }
    }
}