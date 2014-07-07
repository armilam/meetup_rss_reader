package com.example.myrssreader.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.model.NewsStory;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView headlines;
    private List<String> headlineTitles = new ArrayList<String>();
    private List<NewsStory> stories = new ArrayList<NewsStory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headlines = (ListView) findViewById(R.id.list_headlines);

        new DownloadHeadlines(this).execute();

        headlines.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, headlineTitles));
        headlines.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void onStoriesDownloaded(List<NewsStory> newsStories) {
        Log.e("TAG", "Downloaded");
        ArrayAdapter adapter = (ArrayAdapter<String>)headlines.getAdapter();
        for(NewsStory story : newsStories){
            if(story.getTitle() != null) {
                adapter.add(story.getTitle());
                this.stories.add(story);
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("TAG", "Story Url: " + this.stories.get(position).getLink());
        NewsStory story = stories.get(position);
//        Intent intent = StoryText.instance(this, story.getLink(), story.getTitle(), story.getStory());
        Intent intent = StoryViewActivity.instance(this, story.getLink(), story.getTitle());
        startActivity(intent);
    }
}
