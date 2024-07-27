package com.kucw.security.security;

import com.kucw.security.dao.OAuth2MemberDao;
import com.kucw.security.model.OAuth2Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

// 用來處理 OAuth 2.0 + OpenID Connect 的社交登入（ex: Google）
@Component
public class MyOidcUserService extends OidcUserService {

    @Autowired
    private OAuth2MemberDao oAuth2MemberDao;

    @Override
    public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(oidcUserRequest);

        // 取得 oidcUser 和 oidcUserRequest 中的資訊
        String email = Objects.toString(oidcUser.getAttributes().get("email"), null);
        String name = Objects.toString(oidcUser.getAttributes().get("name"), null);

        String provider = oidcUserRequest.getClientRegistration().getRegistrationId();
        String providerId = Objects.toString(oidcUser.getAttributes().get("sub"), null);

        String accessToken = oidcUserRequest.getAccessToken().getTokenValue();
        Date expiresAt = Date.from(oidcUserRequest.getAccessToken().getExpiresAt());

        // 從資料庫查詢此 provider + providerId 組合的 oauth2 member 是否存在
        OAuth2Member oAuth2Member = oAuth2MemberDao.getOAuth2Member(provider, providerId);

        // 如果 oauth2 member 不存在，就創建一個新的 member
        if (oAuth2Member == null) {
            OAuth2Member newOAuth2Member = new OAuth2Member();
            newOAuth2Member.setProvider(provider);
            newOAuth2Member.setProviderId(providerId);
            newOAuth2Member.setName(name);
            newOAuth2Member.setEmail(email);
            newOAuth2Member.setAccessToken(accessToken);
            newOAuth2Member.setExpiresAt(expiresAt);

            oAuth2MemberDao.createOAuth2Member(newOAuth2Member);
        }

        // 返回 Spring Security 原本的 oidcUser
        return oidcUser;
    }
}
