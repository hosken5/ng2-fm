package com.yimei.cron.domain;

/**
 * Created by hongpf on 17/1/5.
 */
public class Menu {
    private String url  ;
    private String name ;
    public Menu(String url, String name){
        this.url = url ;
        this.name =name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
