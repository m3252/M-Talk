package com.msc.mtalk.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Keycloak!";
    }


    @RequestMapping("/members")
    public String members(){
        return "members permitAll!";
    }

}
