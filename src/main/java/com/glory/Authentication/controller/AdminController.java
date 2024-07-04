package com.glory.Authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/admin")
@RestController
public class AdminController {

    @PostMapping
    public String HomeController(String user){
        return "access granted for admin" + user ;
    }
}
