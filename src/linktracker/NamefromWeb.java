package linktracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Read Name from web source.....................
public class NamefromWeb {

    public static void main(String[] args) throws Exception {
        try {
            // Connection with the browser...
            Document doc = Jsoup.connect("https://www.goodfirms.co/directory/country/top-software-development-companies/us").get();
            Elements tmp = doc.select("div.col-md-12 padding30 csmallimage overflow");
            int i = 0;
            // For class type div col-md-12 padding30 csmallimage overflow
            for (Element companylist : tmp) {
                i++;
                System.err.println(i + " " + companylist.getElementsByTag("a.title").first().text());
            }
        } catch (Exception e) {
        }
    }
}
