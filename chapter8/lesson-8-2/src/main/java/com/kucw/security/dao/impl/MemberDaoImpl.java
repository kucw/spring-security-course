package com.kucw.security.dao.impl;

import com.kucw.security.dao.MemberDao;
import com.kucw.security.model.Member;
import com.kucw.security.rowmapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberRowMapper memberRowMapper;

    @Override
    public Member getMemberByEmail(String email) {
        String sql = "SELECT member_id, email, password, name, age FROM member WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, memberRowMapper);

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }
}
