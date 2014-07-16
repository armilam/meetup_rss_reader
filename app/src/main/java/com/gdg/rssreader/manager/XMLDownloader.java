package com.gdg.rssreader.manager;

import android.os.AsyncTask;

import com.gdg.rssreader.manager.xml.RssHandler;
import com.gdg.rssreader.model.RssItem;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rx.subjects.BehaviorSubject;

public class XMLDownloader extends AsyncTask<Void, Void, Void>{
    private BehaviorSubject<RssItem> feedItems;
    private String feedUrl;

    public XMLDownloader(BehaviorSubject<RssItem> feedItems, String feedUrl){
        this.feedItems = feedItems;
        this.feedUrl = feedUrl;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLReader reader = parser.getXMLReader();
            RssHandler handler = new RssHandler(feedItems);
            InputSource input = new InputSource(new URL(feedUrl).openStream());

            reader.setContentHandler(handler);
            reader.parse(input);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
