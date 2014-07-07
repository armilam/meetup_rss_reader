package com.example.manager;

import android.util.Log;

import com.example.model.NewsStory;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoryManager {
    private static StoryManager ourInstance = new StoryManager();

    public static StoryManager getInstance() {
        return ourInstance;
    }

    private StoryManager() {
    }

    private List<NewsStory> stories;

    public List<NewsStory> getStories() throws StoryDownloadException {
        if(stories == null){
           stories = downloadStories();
        }

        return stories;
    }

    private List<NewsStory> downloadStories() throws StoryDownloadException {
        List<NewsStory> downloaded = new ArrayList<NewsStory>();

        OkHttpClient client = new OkHttpClient();
        String url = "https://news.google.com/?topic=s&output=rss";
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            Log.e("TAG", "Response: " + response.body().string());
        } catch (IOException e) {
            throw new StoryDownloadException(e);
        }


        return downloaded;
    }


    public class StoryDownloadException extends Exception{

        public StoryDownloadException(Exception e){
           super(e);
        }
    }

}
