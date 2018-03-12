package com.example.solr.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String URL;

    @Bean
    public SolrServer solrServer() {
        HttpSolrServer solrServer = new HttpSolrServer(URL);
        return  solrServer;
    }
}
