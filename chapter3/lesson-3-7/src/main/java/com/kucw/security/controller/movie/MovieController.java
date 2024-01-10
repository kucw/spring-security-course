package com.kucw.security.controller.movie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @RequestMapping("/getMovies")
    public String getMovies() {
        return "取得電影列表";
    }

    @RequestMapping("/watchFreeMovie")
    public String watchFreeMovie() {
        return "觀看免費電影";
    }

    @RequestMapping("/watchVipMovie")
    public String watchVipMovie() {
        return "觀看 VIP 付費電影";
    }

    @RequestMapping("/uploadMovie")
    public String uploadMovie() {
        return "上傳新電影";
    }

    @RequestMapping("/deleteMovie")
    public String deleteMovie() {
        return "刪除電影";
    }
}
