package com.githubIssueTrack.githubIssueTrack.githubIssue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;

    @Value("${github.auth.token}")
    private String authToken;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(githubApiBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer ->
                                configurer.defaultCodecs().maxInMemorySize(250 * 1024 * 1024)) // 20MB
                        .build())
                .clientConnector(new ReactorClientHttpConnector());
    }
}
