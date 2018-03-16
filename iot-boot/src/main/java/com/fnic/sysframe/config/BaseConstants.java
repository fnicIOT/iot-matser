package com.fnic.sysframe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath:application.properties")
public class BaseConstants {

    @Value("${default.tenantId}")
    public static String defaultTenantId;

    @Value("${default.password}")
    public static String defaultPassword;
}
