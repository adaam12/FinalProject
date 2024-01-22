package com.example.finalproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "feed_table")
public class FeedModel {
    // below line is to auto increment
    @PrimaryKey(autoGenerate = true)

    private int id;
    private String title;
    private String description;
    private String date;
    private String link;

    public FeedModel( String title, String description, String date, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
    }

    public FeedModel() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

