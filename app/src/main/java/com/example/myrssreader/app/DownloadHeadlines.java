package com.example.myrssreader.app;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

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
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();
            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, null);
            stories = parseXMLAndStoreIt(myparser);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
        return stories;
    }

    @Override
    protected void onPostExecute(List<NewsStory> newsStories) {
        Log.e("TAG", "onPostExecute");
        mainActivity.onStoriesDownloaded(newsStories);
    }

    public List<NewsStory> parseXMLAndStoreIt(XmlPullParser myParser) {
        List<NewsStory> stories = new ArrayList<NewsStory>();
        int event;
        String text=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                NewsStory story = new NewsStory();
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("title")){
                            story.setTitle(text);
                        }
                        else if(name.equals("link")){
                            story.setLink(text);
                        }
                        else if(name.equals("description")){
                            story.setDescription(text);
                        }
                        else{
                        }
                        break;
                }
                stories.add(story);
                event = myParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stories;
    }

}
