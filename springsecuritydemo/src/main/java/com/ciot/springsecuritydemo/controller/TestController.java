package com.ciot.springsecuritydemo.controller;

import com.ciot.springsecuritydemo.model.Client;
import com.ciot.springsecuritydemo.model.MongoOauthUser;
import com.ciot.springsecuritydemo.oauthService.MongoClientDetailsService;
import com.ciot.springsecuritydemo.oauthService.MongoUserDetailsService;
import com.ciot.springsecuritydemo.service.OauthClientService;
import com.ciot.springsecuritydemo.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private OauthClientService oauthClientService;
    @Autowired
    private OauthUserService oauthUserService;
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
    @PostMapping("/client")
    public String createClient(@RequestBody Client client) {
        oauthClientService.createClient(client);
        return "success";
    }
    @PostMapping("/user")
    public String createClient(@RequestBody MongoOauthUser user) {
        oauthUserService.createUser(user);
        return "success";
    }


}
