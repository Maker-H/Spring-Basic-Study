package com.example.hellospring.controller;

import com.example.hellospring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final Memberservice memberservice;

    @Autowired
    public MemberController(Memberservice memberservice) {
        this.memberservice = memberservice;
    }
}
