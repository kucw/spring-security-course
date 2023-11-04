package com.kucw.security.controller;

import com.kucw.security.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @PostMapping("/students")
    public String create(@RequestBody Student student) {
        return "在資料庫中插入一筆 Student 數據";
    }

    @GetMapping("/students/{studentId}")
    public String read(@PathVariable Integer studentId,
                       @RequestBody Student student) {
        return "從資料庫中讀取 Student 數據";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId,
                         @RequestBody Student student) {
        return "更新資料庫中的 Student 數據";
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId) {
        return "刪除資料庫中的 Student 數據";
    }

}
