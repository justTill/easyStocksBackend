package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.models.dtos.PostLoginRequestDto;
import com.mobsys.easyStocks.models.dtos.PostLoginResponseDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterRequestDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterResponseDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(path = "/login")
    public PostLoginResponseDto login(@RequestBody PostLoginRequestDto loginData) {
        return null;
    }

    @PostMapping(path = "/register")
    public PostRegisterResponseDto register(@RequestBody PostRegisterRequestDto registerData) {
        return null;
    }

}