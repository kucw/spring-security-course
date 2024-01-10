package com.kucw.security.dao;

import org.springframework.stereotype.Component;

@Component
public class MyDaoImpl implements MyDao {

    @Override
    public String getMovie() {
        System.out.println("執行 MyDao 的 getMovie 方法");

        return "成功取得電影";
    }

    @Override
    public String deleteMovie() {
        System.out.println("執行 MyDao 的 deleteMovie 方法");

        return "成功刪除電影";
    }
}
