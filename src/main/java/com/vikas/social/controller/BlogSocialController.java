package com.vikas.social.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogSocial") // Base URL for all methods below
public class BlogSocialController {

    // 1. GET Request
    @GetMapping("/test")
    public String checkHealth() {
        return "GET request works!";
    }

    // 2. POST Request (Create)
    @PostMapping("/test")
    public String createData(@RequestBody String data) {
        return "POST request received: " + data;
    }

    // 3. PUT Request (Update)
    @PutMapping("/test")
    public String updateData(@RequestBody String data) {
        return "PUT request received: " + data;
    }

    // 4. DELETE Request
    @DeleteMapping("/test")
    public String deleteData() {
        return "DELETE request works!";
    }
}