package com.gdg.rssreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class StoryViewActivity extends Activity {

    private static final String STORY_URL = "story_url";
    private static final String STORY_TITLE = "story_title";

    public static Intent instance(Context context, String url, String title){
        Intent webView = new Intent(context, StoryViewActivity.class);
        webView.putExtra(STORY_TITLE, title);
        webView.putExtra(STORY_URL, url);
        return webView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);

        String url =  getIntent().getExtras().getString(STORY_URL);
        String title = getIntent().getExtras().getString(STORY_TITLE);
        String story = getIntent().getStringExtra("STORY");
        getActionBar().setTitle(title);
        WebView view = (WebView)findViewById(R.id.web_story);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.story_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
