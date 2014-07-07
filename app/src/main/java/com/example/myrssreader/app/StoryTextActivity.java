package com.example.myrssreader.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class StoryTextActivity extends Activity{
    private static final String STORY_TEXT = "story_text";
    private static final String STORY_URL = "story_url";
    private static final String STORY_TITLE = "story_title";

    public static Intent instance(Context context, String url, String title, String story){
        Intent storyIntent = new Intent(context, StoryTextActivity.class);
        storyIntent.putExtra(STORY_TITLE, title);
        storyIntent.putExtra(STORY_URL, url);
        storyIntent.putExtra(STORY_TEXT, story);
        return storyIntent;
    }

    private TextView storyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_text);

        String text = getIntent().getStringExtra(STORY_TEXT);
        storyText = (TextView)findViewById(R.id.txt_news_story);
        storyText.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.story_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
