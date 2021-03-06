package com.okta.developer.cartservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    //For service discovery, a ReactorLoadBalancerExchangeFilterFunction must be added to the WebClient
    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrations,
                               OAuth2AuthorizedClientRepository oAuth2AuthorizedClients){

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer ->
                        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();

        ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, oAuth2AuthorizedClients);

        oAuth2.setDefaultClientRegistrationId("pricing-client");

        return WebClient.builder()
                .apply(oAuth2.oauth2Configuration())
                .exchangeStrategies(exchangeStrategies)
                .filter(lbFunction)
                .baseUrl("http://pricing/pricing/price")
                .build();

    }

}
