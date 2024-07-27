package com.kucw.security.model;

import java.util.Date;

public class OAuth2Member {

    private Integer oauth2memberId;

    // 表示這個 OAuth2Member 是來自哪一個網站，ex: Google、Facebook
    private String provider;

    // 表示這個 OAuth2Member 在 原網站（ex: Google、Facebook...）中的 id 的值
    private String providerId;

    private String name;
    private String email;
    private String accessToken;
    private Date expiresAt;

    public Integer getOauth2memberId() {
        return oauth2memberId;
    }

    public void setOauth2memberId(Integer oauth2memberId) {
        this.oauth2memberId = oauth2memberId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
