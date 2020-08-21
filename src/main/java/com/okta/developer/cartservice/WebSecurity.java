package com.okta.developer.cartservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;


/*
Spring Boot will auto-configure the application as an OAuth2 client because of the client.registration presence in the YAML.
Add a src/main/java/com/okta/developer/cartservice/WebSecurity.java class to override the auto-configuration,
and configure the application as an OAuth 2.0 resource server.
 */

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorize -> authorize
                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }
}
