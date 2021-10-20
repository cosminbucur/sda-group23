package com.sda.spring.security.memory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class PublicRestApiController {

    @GetMapping("/admin")
    public String roleAdmin() {
        return "accessible by admin roles only";
    }

    @GetMapping("/all")
    public String roleAll() {
        return "accessible by admin and user role";
    }

    // http://localhost:8081/api/admin
    // http://localhost:8081/api/all

    // check authentication

    // check authorization
        // /api/admin - login with both roles
        // /api/all - login with both roles
}
