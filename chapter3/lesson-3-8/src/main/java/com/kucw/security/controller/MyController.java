package com.kucw.security.controller;

import com.kucw.security.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping("/getMovie")
    public String getMovie() {
        System.out.println("執行 MyController 的 getMovie 方法");

        myService.getMovie();

        return "成功取得電影";
    }

    @RequestMapping("/deleteMovie")
    public String deleteMovie() {
        System.out.println("執行 MyController 的 deleteMovie 方法");

        myService.deleteMovie();

        return "成功刪除電影";
    }
}
