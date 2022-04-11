package com.example.sqlitenews;

public class item {
    String nameNews, textNews;

    public item(String nameNews, String textNews) {
        this.nameNews = nameNews;
        this.textNews = textNews;
    }

    public String getName() { return nameNews; }

    public String getText() {
        return textNews;
    }
}
