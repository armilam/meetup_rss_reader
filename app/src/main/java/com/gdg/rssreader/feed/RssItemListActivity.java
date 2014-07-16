package com.gdg.rssreader.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gdg.rssreader.R;
import com.gdg.rssreader.manager.FeedManager;
import com.gdg.rssreader.model.RssItem;

public class RssItemListActivity extends Activity
        implements RssItemListFragment.Callbacks {

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssitem_list);

        twoPane = getResources().getBoolean(R.bool.two_pane);
        if (twoPane) {
            ((RssItemListFragment) getFragmentManager()
                    .findFragmentById(R.id.rssitem_list))
                    .setActivateOnItemClick(true);
        }

    }

    @Override
    public void onItemSelected(int position) {
        RssItem item = FeedManager.getInstance().getFeed().getItems().get(position);
        if (twoPane) {
            RssItemDetailFragment fragment = RssItemDetailFragment.instance(item);
            getFragmentManager().beginTransaction()
                    .replace(R.id.rssitem_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, RssItemDetailActivity.class);
            detailIntent.putExtra(RssItemDetailActivity.ITEM_POSITION, position);
            startActivity(detailIntent);
        }
    }
}
