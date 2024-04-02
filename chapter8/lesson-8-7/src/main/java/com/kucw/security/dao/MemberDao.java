package com.kucw.security.dao;

import com.kucw.security.model.Member;
import com.kucw.security.model.Role;

import java.util.List;

public interface MemberDao {

    // 基本 Member 操作
    Member getMemberByEmail(String email);

    Integer createMember(Member member);

    // 權限相關
    List<Role> getRolesByMemberId(Integer memberId);
}
