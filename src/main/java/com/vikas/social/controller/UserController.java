package com.vikas.social.controller;

import com.vikas.social.pojos.RegisterUserRequest;
import com.vikas.social.pojos.RegisterUserResponse;
import com.vikas.social.pojos.UserLoginRequest;
import com.vikas.social.pojos.UserLoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogSocial")
public class UserController {

    @PostMapping("/signup")
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest){
        return RegisterUserResponse.builder().build();
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest){
        return UserLoginResponse.builder().build();
    }
}
