package com.jamsirat.atmapi.endpoint;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/authorize")
public class AuthenticationEndpoint {


    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
