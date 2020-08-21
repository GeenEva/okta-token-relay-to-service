package com.okta.developer.cartservice;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorize -> authorize
                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }
}
