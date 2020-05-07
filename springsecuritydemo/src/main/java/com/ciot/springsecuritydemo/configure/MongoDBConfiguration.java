package com.ciot.springsecuritydemo.configure;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.annotation.Resource;

@Configuration
public class MongoDBConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean("mongoDbFactory")
    public MongoDbFactory getMongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClientURI(mongoUri));
    }

    @Bean("mongoTemplate")
    @Resource(name = "mongoDbFactory")
    public MongoTemplate getMongoTemplate(MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}
