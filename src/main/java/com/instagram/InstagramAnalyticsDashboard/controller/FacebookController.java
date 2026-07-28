package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPage;
import com.instagram.InstagramAnalyticsDashboard.service.FacebookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facebook")
@CrossOrigin("*")
public class FacebookController {

    private final FacebookService facebookService;

    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GetMapping("/profile")
    public FacebookPage profile() throws Exception {
        return facebookService.fetchPage();
    }

    @PostMapping("/save")
    public String save() throws Exception {
        return facebookService.savePage();
    }

}