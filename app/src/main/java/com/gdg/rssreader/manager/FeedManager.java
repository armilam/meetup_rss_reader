package com.gdg.rssreader.manager;

import com.gdg.rssreader.manager.xml.RssHandler;
import com.gdg.rssreader.model.RssItem;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class FeedManager {
    private static FeedManager instance;
    private BehaviorSubject<RssItem> feedItems;

    private FeedManager() {
    }

    public static FeedManager getInstance() {
        if (instance == null) {
            instance = new FeedManager();
        }
        return instance;
    }

    public Observable<RssItem> followFeedUpdates() {
        downloadStories();
        feedItems = BehaviorSubject.create();
        return feedItems;
    }

    private void downloadStories() {

        OkHttpClient client = new OkHttpClient();
        String url = "https://news.google.com/?topic=s&output=rss";
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                RssHandler handler = new RssHandler(feedItems);

                try {
                    XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                    InputSource source = new InputSource(response.body().byteStream());
//                    reader.setContentHandler(handler);
                    reader.parse(source);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
