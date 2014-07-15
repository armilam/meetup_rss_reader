package com.gdg.rssreader.feed.story;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdg.rssreader.R;
import com.gdg.rssreader.model.RssItem;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class StoryViewActivity extends Activity {

    private static final String STORY_URL = "story_url";
    private static final String STORY_TITLE = "story_title";
    public static final String STORY = "STORY";

    @InjectView(R.id.web_story)
    WebView webView;

    public static Intent instance(Context context, RssItem item){
        Intent webView = new Intent(context, StoryViewActivity.class);
        webView.putExtra(STORY_TITLE, item.getTitle());
        webView.putExtra(STORY_URL, item.getLink());
        return webView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        ButterKnife.inject(this);

        String url =  getIntent().getExtras().getString(STORY_URL);
        loadUrl(url);

        String title = getIntent().getExtras().getString(STORY_TITLE);
        getActionBar().setTitle(title);

        String story = getIntent().getStringExtra(STORY);
        Log.e(StoryViewActivity.class.getSimpleName(), "Story: " + story);
    }

    private void loadUrl(String url){
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
