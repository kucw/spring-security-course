package com.kucw.security.security;

import com.kucw.security.dao.OAuth2MemberDao;
import com.kucw.security.model.OAuth2Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

// 用來處理 OAuth 2.0 的社交登入（ex: GitHub、Facebook、LINE）
@Component
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private OAuth2MemberDao oAuth2MemberDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        // 取得 oAuth2User 和 oAuth2UserRequest 中的資訊
        String email = Objects.toString(oAuth2User.getAttributes().get("email"), null);
        String name = Objects.toString(oAuth2User.getAttributes().get("name"), null);

        String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getName();

        String accessToken = oAuth2UserRequest.getAccessToken().getTokenValue();
        Date expiresAt = Date.from(oAuth2UserRequest.getAccessToken().getExpiresAt());

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

        // 返回 Spring Security 原本的 oAuth2User
        return oAuth2User;
    }
}
