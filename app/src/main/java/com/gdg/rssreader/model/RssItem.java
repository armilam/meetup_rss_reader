package com.gdg.rssreader.model;

public class RssItem {
    private String title;
    private String link;
    private String story;

    public RssItem() {
    }

    public RssItem(String title, String link, String story){
        this.title = title;
        this.link = link;
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

}
