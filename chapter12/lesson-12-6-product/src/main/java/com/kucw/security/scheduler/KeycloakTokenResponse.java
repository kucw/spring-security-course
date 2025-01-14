package com.kucw.security.scheduler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeycloakTokenResponse {

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("expires_in")
    String expiresIn;

    @JsonProperty("id_token")
    String idToken;

    String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
