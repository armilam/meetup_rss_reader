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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
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
        Log.e("TAG", "Story Url: " + this.stories.get(position).getTitle());
        Intent intent = new Intent(this, StoryViewActivity.class);
        intent.putExtra("STORY_URL", "http://www.google.com");

        startActivity(intent);
    }
}
