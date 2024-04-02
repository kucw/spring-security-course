package com.kucw.security.controller;

import com.kucw.security.dao.MemberDao;
import com.kucw.security.dao.RoleDao;
import com.kucw.security.model.Member;
import com.kucw.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MemberController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Member member) {
        // 省略參數檢查 (ex: email 是否被註冊過)

        // hash 原始密碼
        String hashedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(hashedPassword);

        // 在資料庫中插入 Member 數據
        Integer memberId = memberDao.createMember(member);

        // 為 Member 添加預設的 Role
        Role normalRole = roleDao.getRoleByName("ROLE_NORMAL_MEMBER");
        memberDao.addRoleForMemberId(memberId, normalRole);

        return "註冊成功";
    }

    @PostMapping("/userLogin")
    public String userLogin(Authentication authentication) {
        // 帳號密碼驗證由 Spring Security 處理，能執行到這裡表示已經登入成功

        // 取得使用者的帳號
        String username = authentication.getName();

        // 取得使用者的權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return "登入成功！帳號 " + username + " 的權限為: " + authorities;
    }
}
