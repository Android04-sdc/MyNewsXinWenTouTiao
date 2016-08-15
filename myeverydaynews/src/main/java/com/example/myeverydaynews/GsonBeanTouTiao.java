package com.example.myeverydaynews;

/**
 * Created by Administrator on 2016/8/9.
 */
public class GsonBeanTouTiao {
    private String url;
    private String image;
    private String title;

    public GsonBeanTouTiao(String url, String title, String image) {
        this.url = url;
        this.title = title;
        this.image = image;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
