package com.yimei.cron.domain;

import java.io.Serializable;

/**
 * Created by hongpf on 16/12/16.
 */
public class Team   implements Serializable {
    private  String name ;
    private  Long id ;
    private  String message ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
