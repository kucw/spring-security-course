SELECT member.member_id, member.email, role.role_name FROM member
    JOIN member_has_role ON member.member_id = member_has_role.member_id
    JOIN role ON member_has_role.role_id = role.role_id;