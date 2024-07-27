package com.kucw.security.rowmapper;

import com.kucw.security.model.OAuth2Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OAuth2MemberRowMapper implements RowMapper<OAuth2Member> {

    @Override
    public OAuth2Member mapRow(ResultSet resultSet, int i) throws SQLException {
        OAuth2Member OAuth2Member = new OAuth2Member();
        OAuth2Member.setOauth2memberId(resultSet.getInt("oauth2_member_id"));
        OAuth2Member.setProvider(resultSet.getString("provider"));
        OAuth2Member.setProviderId(resultSet.getString("provider_id"));
        OAuth2Member.setName(resultSet.getString("name"));
        OAuth2Member.setEmail(resultSet.getString("email"));
        OAuth2Member.setAccessToken(resultSet.getString("access_token"));
        OAuth2Member.setExpiresAt(resultSet.getTimestamp("expires_at"));

        return OAuth2Member;
    }
}
