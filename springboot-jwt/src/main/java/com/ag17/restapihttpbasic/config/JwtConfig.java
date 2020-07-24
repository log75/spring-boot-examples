package com.ag17.restapihttpbasic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alireza on 6/22/20.
 */
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer TokenExpirationAfterDays;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return TokenExpirationAfterDays;
    }

    public String getHeader() {
        return "Authorization";
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        TokenExpirationAfterDays = tokenExpirationAfterDays;
    }
}
