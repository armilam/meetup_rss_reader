package com.example.model;

public class NewsStory {
    private String title;
    private String link;
    private String story;

    public NewsStory(String title, String link, String story){
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
