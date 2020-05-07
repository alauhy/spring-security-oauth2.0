package com.ciot.springsecuritydemo.oauthService;

import com.ciot.springsecuritydemo.model.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("mongoUserDetailsService")
public class MongoUserDetailsService implements UserDetailsService {
    private final String USER_COLLECTION = "mongo_oauth_user";
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OauthUser user = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), OauthUser.class, USER_COLLECTION);
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        return user;
    }

    public void addUser(UserDetails userDetails) throws UsernameNotFoundException {
        mongoTemplate.insert(userDetails, USER_COLLECTION);
    }
}
