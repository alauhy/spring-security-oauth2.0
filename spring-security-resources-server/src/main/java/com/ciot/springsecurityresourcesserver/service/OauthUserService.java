package com.ciot.springsecurityresourcesserver.service;

import com.ciot.springsecurityresourcesserver.model.MongoOauthUser;
import com.ciot.springsecurityresourcesserver.oauthService.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OauthUserService {
    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createUser(MongoOauthUser user) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User mongoOauthUser = new User(user.getUsername(), user.getPassword(), list);
        mongoUserDetailsService.addUser(mongoOauthUser);
    }
}
