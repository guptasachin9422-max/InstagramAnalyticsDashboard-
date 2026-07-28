package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import com.instagram.InstagramAnalyticsDashboard.service.AnalyticsService;

import com.instagram.InstagramAnalyticsDashboard.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    @Autowired
    private MediaRepository mediaRepository;
     @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/overall")
    public Map<String, Object> getOverallAnalytics() {
        return analyticsService.getOverallAnalytics();
    }

    @GetMapping
    public Map<String, Object> analytics() {

        List<Media> mediaList = mediaRepository.findAll();

        int image = 0;
        int video = 0;
        int reel = 0;

        for (Media media : mediaList) {

            if ("IMAGE".equalsIgnoreCase(media.getMediaType()))
                image++;

            else if ("VIDEO".equalsIgnoreCase(media.getMediaType()))
                video++;

            else if ("REELS".equalsIgnoreCase(media.getMediaType())
                    || "REEL".equalsIgnoreCase(media.getMediaType()))
                reel++;
        }

        Map<String, Object> map = new HashMap<>();

        map.put("Total Posts", mediaList.size());
        map.put("Images", image);
        map.put("Videos", video);
        map.put("Reels", reel);

        return map;
    }

}