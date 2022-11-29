package com.works.xml;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

@Service
public class XmlService {

    public List<Currency> data() {
        List<Currency> ls = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String xmlString  = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(xmlString, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for (Element item : elements) {
                String ForexBuying = item.getElementsByTag("ForexBuying").text();
                String ForexSelling = item.getElementsByTag("ForexSelling").text();
                Currency c = new Currency(ForexBuying, ForexSelling);
                ls.add(c);
            }
        }catch (Exception ex) {
            System.err.println("xml err : " + ex);
        }
        return ls;
    }


}
