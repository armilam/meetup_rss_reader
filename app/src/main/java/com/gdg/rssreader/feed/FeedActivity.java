package com.gdg.rssreader.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gdg.rssreader.R;
import com.gdg.rssreader.feed.story.StoryViewActivity;
import com.gdg.rssreader.manager.FeedManager;
import com.gdg.rssreader.model.RssItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FeedActivity extends Activity implements AdapterView.OnItemClickListener {

    private FeedManager feedManager = FeedManager.getInstance();
    private ArrayAdapter<String> headlinesAdapter;

    @InjectView(R.id.list_headlines)
    ListView headlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpHeadlinesList();
        getStories();
    }

    private void setUpHeadlinesList() {
        headlinesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        headlines.setAdapter(this.headlinesAdapter);
        headlines.setOnItemClickListener(this);
    }

    private void getStories() {
        feedManager.followFeedUpdates()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RssItem>() {
                    @Override
                    public void call(RssItem rssItem) {
                        headlinesAdapter.add(rssItem.getTitle());
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssItem story = feedManager.getFeed().getItems().get(position);
        Intent intent = StoryViewActivity.instance(this, story);
        startActivity(intent);
    }

}
