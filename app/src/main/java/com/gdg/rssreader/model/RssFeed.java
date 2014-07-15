package com.gdg.rssreader.model;

import java.util.ArrayList;
import java.util.List;

public class RssFeed {
    private List<RssItem> items;

    public RssFeed() {
        this.items = new ArrayList<RssItem>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public List<RssItem> getItems() {
        return items;
    }

    public void addItem(RssItem item) {
        this.items.add(item);
    }
}
