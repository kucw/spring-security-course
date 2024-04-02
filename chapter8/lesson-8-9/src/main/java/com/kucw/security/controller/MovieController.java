package com.kucw.security.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/getMovies")
    public String getMovies() {
        return "取得電影列表";
    }

    @PostMapping("/watchFreeMovie")
    public String watchFreeMovie() {
        return "觀看免費電影";
    }

    @PostMapping("/watchVipMovie")
    public String watchVipMovie() {
        return "觀看 VIP 付費電影";
    }

    @PostMapping("/uploadMovie")
    public String uploadMovie() {
        return "上傳新電影";
    }

    @DeleteMapping("/deleteMovie")
    public String deleteMovie() {
        return "刪除電影";
    }
}
