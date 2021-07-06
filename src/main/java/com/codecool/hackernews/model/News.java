package com.codecool.hackernews.model;

import java.io.Serializable;

public class News  implements Serializable {
    private String title;
    private String time_ago;
    private String user;
    private String url;

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public String getTimeAgo() {
        return time_ago;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

}