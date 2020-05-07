package com.ciot.springsecurityresourcesserver.service;


import com.ciot.springsecurityresourcesserver.model.Client;
import com.ciot.springsecurityresourcesserver.oauthService.MongoClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class OauthClientService {
    @Autowired
    private MongoClientDetailsService mongoClientDetailsService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void createClient(Client client) {
        BaseClientDetails clientDetails = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScopes(), client.getGrantTypes(), client.getAuthorities());
        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
        clientDetails.setClientSecret(client.getClientSecret());
        mongoClientDetailsService.addClientDetails(clientDetails);
    }
}
