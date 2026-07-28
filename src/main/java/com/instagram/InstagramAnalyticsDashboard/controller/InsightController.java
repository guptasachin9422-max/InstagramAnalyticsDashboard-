package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insights")
@CrossOrigin("*")
public class InsightController {

    @Autowired
    private InsightService insightService;

    @GetMapping
    public String getInsights() {
        return insightService.getInsights();
    }

    @PostMapping("/save")
    public String saveInsights() throws Exception {
        return insightService.saveInsights();
    }

}