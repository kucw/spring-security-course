package com.kucw.security.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DebugServiceImpl implements DebugService {

    @Override
    public List<String> getAllNames() {
        List<String> nameList = new ArrayList<>();

        nameList.add("Judy");
        nameList.add("Amy");
        nameList.add("Mark");

        return nameList;
    }
}