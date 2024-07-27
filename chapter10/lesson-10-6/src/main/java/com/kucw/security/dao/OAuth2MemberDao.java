package com.kucw.security.dao;

import com.kucw.security.model.OAuth2Member;

public interface OAuth2MemberDao {

    OAuth2Member getOAuth2Member(String provider, String providerId);

    Integer createOAuth2Member(OAuth2Member OAuth2Member);
}
