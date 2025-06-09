package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandler() {
        return "this is home controller";
    }

    // This prevents 404/502 errors for favicon.ico requests
    @GetMapping("/favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
        // Do nothing (satisfies the browser's request silently)
    }
}
