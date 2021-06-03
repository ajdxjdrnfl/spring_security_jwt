package com.example.ajdxjdrnfl.test.controller;

import com.example.ajdxjdrnfl.test.dto.LoginForm;
import com.example.ajdxjdrnfl.test.service.JwtLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    JwtLoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam(value = "ID") final String id,
            @RequestParam(value = "pwd") final String pwd) throws Exception {

        return loginService.Login(new LoginForm(id, pwd));

    }

    @RequestMapping("/home")
    public String home() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping("/temp")
    public String temp() {
        return "hello world";
    }
}
