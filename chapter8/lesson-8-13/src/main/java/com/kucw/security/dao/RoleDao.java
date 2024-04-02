package com.kucw.security.dao;

import com.kucw.security.model.Role;

public interface RoleDao {

    Role getRoleByName(String roleName);
}
