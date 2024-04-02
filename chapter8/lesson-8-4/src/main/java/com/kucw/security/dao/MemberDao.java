package com.kucw.security.dao;

import com.kucw.security.model.Member;

public interface MemberDao {

    // 基本 Member 操作
    Member getMemberByEmail(String email);

    Integer createMember(Member member);
}
