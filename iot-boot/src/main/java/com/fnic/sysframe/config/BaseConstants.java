package com.fnic.sysframe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath:application.properties")
public class BaseConstants {

    @Value("${default.tenantId}")
    public String defaultTenantId;

    @Value("${default.password}")
    public String defaultPassword;

    @Value("${default.haothink.tenantId}")
    public String defaultHaoThinkTenantId;

    @Value("${default.haothink.customerId}")
    public String defaultHaoThinkCustomerId;

    @Value("${default.huali.tenantId}")
    public String defaultHualiTenantId;

    @Value("${default.huali.customerId}")
    public String defaultHualiCustomerId;

    public String getDefaultTenantId() {
        return defaultTenantId;
    }

    public void setDefaultTenantId(String defaultTenantId) {
        this.defaultTenantId = defaultTenantId;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public String getDefaultHaoThinkTenantId() {
        return defaultHaoThinkTenantId;
    }

    public void setDefaultHaoThinkTenantId(String defaultHaoThinkTenantId) {
        this.defaultHaoThinkTenantId = defaultHaoThinkTenantId;
    }

    public String getDefaultHaoThinkCustomerId() {
        return defaultHaoThinkCustomerId;
    }

    public void setDefaultHaoThinkCustomerId(String defaultHaoThinkCustomerId) {
        this.defaultHaoThinkCustomerId = defaultHaoThinkCustomerId;
    }
}
