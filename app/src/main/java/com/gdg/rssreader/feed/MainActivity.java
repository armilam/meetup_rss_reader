package com.gdg.rssreader.feed;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gdg.rssreader.R;
import com.gdg.rssreader.manager.FeedManager;
import com.gdg.rssreader.model.RssItem;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {


    private RecyclerView feedItems;
    private RecyclerView.Adapter feedItemsAdapter;
    private RecyclerView.LayoutManager feedItemsLayoutManager;
    private List<RssItem> rssItems = new ArrayList<RssItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FeedManager.getInstance().followFeedUpdates()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RssItem>() {
                    @Override
                    public void call(RssItem rssItem) {
                        rssItems.add(rssItem);
                        feedItemsAdapter.notifyDataSetChanged();
                    }
                });

        feedItems = (RecyclerView) findViewById(R.id.recycler_rss);
        feedItems.setHasFixedSize(true);
        feedItems.setElevation(100);
        feedItems.setAlpha(0.5f);
        feedItemsLayoutManager = new LinearLayoutManager(this);
        feedItems.setLayoutManager(feedItemsLayoutManager);
        feedItemsAdapter = new RssItemAdapter(rssItems);
        feedItems.setAdapter(feedItemsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
