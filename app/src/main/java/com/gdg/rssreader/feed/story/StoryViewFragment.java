package com.gdg.rssreader.feed.story;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdg.rssreader.R;
import com.gdg.rssreader.model.RssItem;

import butterknife.InjectView;


public class StoryViewFragment extends Fragment {

    private static final String STORY_URL = "story_url";
    private static final String STORY_TITLE = "story_title";
    public static final String STORY = "STORY";

    @InjectView(R.id.web_story)
    WebView webView;

    public static StoryViewFragment instance(RssItem item){
        StoryViewFragment fragment = new StoryViewFragment();
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_story_view, container);
        return v;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_story_view);
//        ButterKnife.inject(this);
//
//        String url =  getIntent().getExtras().getString(STORY_URL);
//        loadUrl(url);
//
//        String title = getIntent().getExtras().getString(STORY_TITLE);
//        getActionBar().setTitle(title);
//
//        String story = getIntent().getStringExtra(STORY);
//        Log.e(StoryViewActivity.class.getSimpleName(), "Story: " + story);
//    }

    private void loadUrl(String url){
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
