package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.InstagramAccount;
import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import com.instagram.InstagramAnalyticsDashboard.repository.InstagramRepository;
import com.instagram.InstagramAnalyticsDashboard.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @GetMapping
    public Map<String, Object> dashboard() {

        Map<String, Object> response = new HashMap<>();

        List<InstagramAccount> accounts = instagramRepository.findAll();
        List<Media> media = mediaRepository.findAll();

        response.put("profile", accounts.isEmpty() ? null : accounts.get(0));
        response.put("totalPosts", media.size());
        response.put("media", media);

        return response;
    }

}