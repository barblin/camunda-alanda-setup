package com.alanda.process.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "process.manager.camunda")
public class ProcessManagerConfig {
    private String api;
    private String upload;
    private boolean enableDuplicateFiltering;
    private boolean deployChangedOnly;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public boolean isEnableDuplicateFiltering() {
        return enableDuplicateFiltering;
    }

    public boolean isDeployChangedOnly() {
        return deployChangedOnly;
    }
}