package com.ciot.springsecurityresourcesserver.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public class MongoOauthUser {
    public static final List<GrantedAuthority> NO_AUTHORITIES = Collections.emptyList();
    String password;
    String username;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
