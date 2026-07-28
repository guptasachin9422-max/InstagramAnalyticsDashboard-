package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPost;
import com.instagram.InstagramAnalyticsDashboard.service.FacebookPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facebook")
@CrossOrigin("*")
public class FacebookPostController {

    private final FacebookPostService service;

    public FacebookPostController(FacebookPostService service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public List<FacebookPost> fetchPosts() throws Exception {
        return service.fetchPosts();
    }

    @PostMapping("/posts/save")
    public String savePosts() throws Exception {
        return service.savePosts();
    }

    @GetMapping("/posts/all")
    public List<FacebookPost> getAllPosts() {
        return service.getAllPosts();
    }

}