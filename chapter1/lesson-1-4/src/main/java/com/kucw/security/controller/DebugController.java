package com.kucw.security.controller;

import com.kucw.security.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DebugController {

    @Autowired
    private DebugService debugService;

    @GetMapping("/debug")
    public String debug() {
        Double number = 123 / 10.0;

        List<String> nameList = debugService.getAllNames();

        return "Finish debug";
    }
}
