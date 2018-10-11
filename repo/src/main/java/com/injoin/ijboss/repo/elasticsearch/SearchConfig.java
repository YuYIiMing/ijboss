package com.injoin.ijboss.repo.elasticsearch;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories("com.injoin.ijboss.repo.elasticsearch.repository")
public class SearchConfig {
}
