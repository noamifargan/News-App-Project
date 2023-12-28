package com.example.myapplication3;

import java.lang.ref.SoftReference;

public class item {
    private String title;
    private String pubDate;
    private String description;
    private String thumbnail;
    private String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public item(String title, String description, String link, String pubDate, String image) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.thumbnail = image;
        this.link=link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return thumbnail;
    }

    public void setImage(String image) {
        this.thumbnail = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }
}
