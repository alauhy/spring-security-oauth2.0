package com.ciot.springsecurityresourcesserver.oauthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mongoClientDetailsService")
public class MongoClientDetailsService implements ClientDetailsService {
    private final String COLLECTION_NAME = "oauth_client_details";
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private MongoTemplate mongoTemplate;

    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails client = mongoTemplate.findOne(new Query(Criteria.where("ClientId").is(clientId)), BaseClientDetails.class, COLLECTION_NAME);
        if (client != null) {
            String secret = client.getClientSecret();
            if (!(secret == null || "".equals(secret))) {
                client.setClientSecret(passwordEncoder.encode(secret));
            }
        }
        return client;
    }

    public void addClientDetails(ClientDetails clientDetails) {
        mongoTemplate.insert(clientDetails, COLLECTION_NAME);
    }

    public void updateClientDetails(ClientDetails clientDetails) {
        Update update = new Update();
        update.set("resourceIds", clientDetails.getResourceIds());
        update.set("clientSecret", clientDetails.getClientSecret());
        update.set("authorizedGrantTypes", clientDetails.getAuthorizedGrantTypes());
        update.set("registeredRedirectUri", clientDetails.getRegisteredRedirectUri());
        update.set("authorities", clientDetails.getAuthorities());
        update.set("accessTokenValiditySeconds", clientDetails.getAccessTokenValiditySeconds());
        update.set("refreshTokenValiditySeconds", clientDetails.getRefreshTokenValiditySeconds());
        update.set("additionalInformation", clientDetails.getAdditionalInformation());
        update.set("scope", clientDetails.getScope());
        mongoTemplate.updateFirst(new Query(Criteria.where("clientId").is(clientDetails.getClientId())), update, COLLECTION_NAME);
    }

    public void updateClientSecret(String clientId, String secret) {
        Update update = new Update();
        update.set("clientSecret", secret);
        mongoTemplate.updateFirst(new Query(Criteria.where("clientId").is(clientId)), update, COLLECTION_NAME);
    }

    public void removeClientDetails(String clientId) {
        mongoTemplate.remove(new Query(Criteria.where("clientId").is(clientId)), COLLECTION_NAME);
    }

    public List<ClientDetails> listClientDetails() {
        return mongoTemplate.findAll(ClientDetails.class, COLLECTION_NAME);
    }
}
