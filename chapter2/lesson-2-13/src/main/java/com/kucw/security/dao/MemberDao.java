package com.kucw.security.dao;

import com.kucw.security.model.Member;

public interface MemberDao {

    Member getMemberById(Integer memberId);

    Member getMemberByEmail(String email);

    Integer createMember(Member member);
}
