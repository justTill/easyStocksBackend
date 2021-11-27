package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.persistence.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping(path="/login")
    public String login(@RequestBody User user)
    {
        return "login";
    }

    @PostMapping(path="/register")
    public String register(@RequestBody User user)
    {
        return "register";
    }

}