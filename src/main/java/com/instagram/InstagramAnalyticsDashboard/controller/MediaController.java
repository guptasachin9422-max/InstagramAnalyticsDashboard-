package com.instagram.InstagramAnalyticsDashboard.controller;

import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import com.instagram.InstagramAnalyticsDashboard.service.MediaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/fetch")
    public String fetchMedia() {
        return mediaService.getMedia();
    }

    @PostMapping("/save")
    public String saveMedia() {
        return mediaService.saveMedia();
    }

    @PutMapping("/update")
    public String updateAnalytics() {
        return mediaService.updateAnalytics();
    }

    @GetMapping
    public List<Media> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{id}")
    public Optional<Media> getMedia(@PathVariable Long id) {
        return mediaService.getMediaById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return "Media Deleted Successfully";
    }

    @GetMapping("/search")
    public List<Media> search(@RequestParam String caption) {
        return mediaService.searchByCaption(caption);
    }

    @GetMapping("/count")
    public long totalMedia() {
        return mediaService.getTotalMediaCount();
    }

    @GetMapping("/likes")
    public int totalLikes() {
        return mediaService.getTotalLikes();
    }

    @GetMapping("/comments")
    public int totalComments() {
        return mediaService.getTotalComments();
    }

    @GetMapping("/views")
    public int totalViews() {
        return mediaService.getTotalViews();
    }
    @GetMapping("/most-viewed")
public ResponseEntity<?> getMostViewedReel() {

    Optional<Media> media = mediaService.getMostViewedReel();

    if (media.isPresent()) {
        return ResponseEntity.ok(media.get());
    }

    return ResponseEntity.notFound().build();
}
}