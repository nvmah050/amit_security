package com.amit.springsercurity.controller;

import com.amit.springsercurity.model.ApiException;
import com.amit.springsercurity.model.MainResponse;
import com.amit.springsercurity.model.request.AddUserRequest;
import com.amit.springsercurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public MainResponse<String> createdUser(@RequestBody AddUserRequest request) throws ApiException{
        return userService.createdUser(request);
    }
}
