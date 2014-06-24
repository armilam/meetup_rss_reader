package com.example.myrssreader.app;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadHeadlines extends AsyncTask<Void, Void, List<NewsStory>>{

    private XmlPullParserFactory xmlFactoryObject;
    private MainActivity mainActivity;

    public DownloadHeadlines(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<NewsStory> doInBackground(Void... params) {
        //https://news.google.com/?output=rss

        Log.e("TAG", "doInBG");
        List<NewsStory> stories = null;
        InputStream stream;
        try {
            URL url = new URL("https://news.google.com/?output=rss");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            stream = conn.getInputStream();

            stories = parse(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return stories;
    }

    private List<NewsStory> parse(InputStream inputStream) throws XmlPullParserException, IOException{
        try {
            XmlPullParser pullParser = Xml.newPullParser();
            pullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            pullParser.setInput(inputStream, null);
            pullParser.nextTag();
            pullParser.nextTag();
            return parseXml(pullParser);
        }finally {
            inputStream.close();
        }
    }

    @Override
    protected void onPostExecute(List<NewsStory> newsStories) {
        Log.e("TAG", "onPostExecute");
        mainActivity.onStoriesDownloaded(newsStories);
    }

    private List<NewsStory> parseXml(XmlPullParser parser) throws XmlPullParserException, IOException{
        List<NewsStory> stories = new ArrayList<NewsStory>();

        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if(name.equals("item")){
                stories.add(readEntry(parser));
            }else{
                skip(parser);
            }
        }

        return stories;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if(parser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }
        
        int depth = 1;
        while(depth != 0){
            switch(parser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private NewsStory readEntry(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "item");

        String title = null, link = null, description = null;

        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            String name = parser.getName();
            if(name.equals("title")){
               title = readTitle(parser);
            }else if(name.equals("link")) {
               link = readLink(parser);
            }else if(name.equals("description")){
               description = readDescription(parser);
            }else{
               skip(parser);
            }
        }
        return new NewsStory(title, link, description);
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "title");

        return title;
    }

    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "link");

        return link;
    }

    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "description");

        return description;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";

        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        return result;
    }

}
