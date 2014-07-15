package com.gdg.rssreader.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.gdg.rssreader.R;
import com.gdg.rssreader.manager.FeedManager;
import com.gdg.rssreader.model.RssItem;

public class RssItemDetailActivity extends Activity {

    public static final String ITEM_POSITION = "item_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssitem_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        int position = getIntent().getIntExtra(ITEM_POSITION, -1);
        if (savedInstanceState == null) {
            RssItem item = FeedManager.getInstance().getFeed().getItems().get(position);
            RssItemDetailFragment fragment = RssItemDetailFragment.instance(item);


            getFragmentManager().beginTransaction()
                    .add(R.id.rssitem_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, RssItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
