package com.instagram.InstagramAnalyticsDashboard.service;

import com.instagram.InstagramAnalyticsDashboard.entity.InstagramAccount;
import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import com.instagram.InstagramAnalyticsDashboard.repository.InstagramRepository;
import com.instagram.InstagramAnalyticsDashboard.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public Map<String, Object> getOverallAnalytics() {

    InstagramAccount account = instagramRepository.findAll().get(0);

    List<Media> mediaList = mediaRepository.findAll();

    int totalLikes = 0;
    int totalComments = 0;
    int totalViews = 0;

    int images = 0;
    int videos = 0;
    int reels = 0;

    for (Media media : mediaList) {

        totalLikes += media.getLikeCount() == null ? 0 : media.getLikeCount();
        totalComments += media.getCommentsCount() == null ? 0 : media.getCommentsCount();
        totalViews += media.getViews() == null ? 0 : media.getViews();

     switch (media.getMediaType().toUpperCase()) {

    case "IMAGE":
        images++;
        break;

    case "VIDEO":
        videos++;
        break;

    case "REEL":
        reels++;
        break;

    case "CAROUSEL_ALBUM":
        images++;
        break;
}
    
}

    Map<String, Object> response = new HashMap<>();

    response.put("followers", account.getFollowersCount());
    response.put("following", account.getFollowingCount());
    response.put("posts", account.getPostCount());

    response.put("totalPostsSaved", mediaList.size());

    response.put("totalLikes", totalLikes);
    response.put("totalComments", totalComments);
    response.put("totalViews", totalViews);

    response.put("images", images);
    response.put("videos", videos);
    response.put("reels", reels);

    return response;
}
}
