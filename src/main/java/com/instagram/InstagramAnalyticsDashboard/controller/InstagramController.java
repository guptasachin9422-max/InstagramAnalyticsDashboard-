package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.InstagramAccount;
import com.instagram.InstagramAnalyticsDashboard.service.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instagram")
@CrossOrigin("*")
public class InstagramController {

    @Autowired
    private InstagramService service;
    @GetMapping("/profile")
public InstagramAccount profile() throws Exception {
    return service.fetchProfile();
}

    // @GetMapping("/profile")
    // public String profile() {
    //     return service.getProfile();
    // }

    @PostMapping("/save")
    public String save() throws Exception {
        return service.saveProfile();
    }

}