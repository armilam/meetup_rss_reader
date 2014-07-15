package com.gdg.rssreader.feed.story;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gdg.rssreader.R;
import com.gdg.rssreader.manager.FeedManager;


public class FeedActivity extends Activity implements AdapterView.OnItemClickListener {

    private FeedManager feedManager = FeedManager.getInstance();
    private ArrayAdapter<String> headlinesAdapter;

//    @InjectView(R.id.list_headlines)
//    ListView headlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setUpHeadlinesList();
    }

//    private void setUpHeadlinesList() {
//        headlinesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
//        get.setAdapter(this.headlinesAdapter);
//        headlines.setOnItemClickListener(this);
//    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        RssItem story = feedManager.getFeed().getItems().get(position);
//        Intent intent = StoryViewFragment.instance(this, story);
//        startActivity(intent);
    }

}
