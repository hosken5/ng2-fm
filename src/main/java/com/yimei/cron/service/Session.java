package com.yimei.cron.service;

import com.yimei.cron.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by hongpf on 17/1/3.
 */

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session  implements Serializable{

    protected User user  ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean login(User  user) {
        this.user =user;
        return true;
    }

    public void logout() {
        this.user = null;
    }

    public boolean isLogined() {
        return this.user != null;
    }

}
