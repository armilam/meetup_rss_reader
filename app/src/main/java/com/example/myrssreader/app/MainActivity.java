package com.example.myrssreader.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView headlines;
    private List<String> headlineTitles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headlines = (ListView) findViewById(R.id.list_headlines);
        new DownloadHeadlines(this).execute();
        headlines.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, headlineTitles));
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
            if(story.getTitle() != null)
                adapter.add(story.getTitle());
        }

    }

}
