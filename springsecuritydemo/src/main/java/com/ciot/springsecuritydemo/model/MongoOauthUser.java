package com.ciot.springsecuritydemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
