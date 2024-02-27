package com.kucw.security.controller.movie;

import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @GetMapping("/getMovie")
    public String getMovie() {
        return "取得電影";
    }

    @PostMapping("/createMovie")
    public String createMovie() {
        return "新增電影";
    }

    @PutMapping("/updateMovie")
    public String updateMovie() {
        return "修改電影";
    }

    @DeleteMapping("/deleteMovie")
    public String deleteMovie() {
        return "刪除電影";
    }
}
