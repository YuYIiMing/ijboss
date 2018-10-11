package com.injoin.ijboss.repo.tablestore.config;

import in.togetu.tablestore.repository.config.EnableTableStoreRepositories;
import in.togetu.tscommon.config.AliyunProductConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableTableStoreRepositories(basePackages = "com.injoin.ijboss.repo.tablestore")
public class RepoConfig {

    @Bean("tsConfig")
    @ConfigurationProperties(prefix = "tablestore")
    public AliyunProductConfig getTableStoreConfig() {
        return new AliyunProductConfig();
    }
}
