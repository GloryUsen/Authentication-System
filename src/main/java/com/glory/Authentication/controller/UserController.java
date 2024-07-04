package com.glory.Authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.NestingKind;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    @PostMapping("/")
    public String HomeController(String user){
        return "access granted for user" + user ;
    }
}
