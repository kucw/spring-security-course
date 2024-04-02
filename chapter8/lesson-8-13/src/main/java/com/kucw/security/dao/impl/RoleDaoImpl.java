package com.kucw.security.dao.impl;

import com.kucw.security.dao.RoleDao;
import com.kucw.security.model.Role;
import com.kucw.security.rowmapper.MemberRowMapper;
import com.kucw.security.rowmapper.RoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberRowMapper memberRowMapper;

    @Autowired
    private RoleRowMapper roleRowMapper;

    @Override
    public Role getRoleByName(String roleName) {
        String sql = "SELECT role_id, role_name FROM role WHERE role_name = :roleName";

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", roleName);

        List<Role> roleList = namedParameterJdbcTemplate.query(sql, map, roleRowMapper);

        if (roleList.isEmpty()) {
            return null;
        } else {
            return roleList.get(0);
        }
    }
}
