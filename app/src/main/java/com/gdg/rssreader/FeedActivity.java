package com.gdg.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gdg.rssreader.manager.FeedManager;
import com.gdg.rssreader.model.RssItem;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FeedActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<String> stories = new ArrayList<String>();
    private FeedManager feedManager = FeedManager.getInstance();
    private ArrayAdapter<String> headlinesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView headlines = (ListView) findViewById(R.id.list_headlines);
        headlinesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stories);
        headlines.setAdapter(this.headlinesAdapter);
        headlines.setOnItemClickListener(this);

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
//        RssItem story = headlinesAdapter.getItem(position);
//        Intent intent = StoryViewActivity.instance(this, story.getLink(), story.getTitle());
//        startActivity(intent);
    }

}
