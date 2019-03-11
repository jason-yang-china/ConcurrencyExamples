package com.epam.concurrent.cryptocurrency;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Crawler {

    private List<String> sites = Arrays.asList("https://www.okcoin.com/", "https://coinmarketcap.com/", "https://web.zb.com/");

    public String getRandomSite() {
        Random random = new Random();
        return sites.get(random.nextInt(2));
    }

    public String downloadSite(final String site) {
        System.out.println("Requeted URL:" + site);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(site);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ site, e);
        } finally {
            try {
                System.out.println("finished crawler");
            }finally {

            }
        }
        return sb.toString();
    }


    public Document getDocument(final String xmlString) throws ParserConfigurationException,IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        return dBuilder.parse(new InputSource(new StringReader(xmlString)));
    }

    private List<String> parseDocument() {
        return null;
    }


}
