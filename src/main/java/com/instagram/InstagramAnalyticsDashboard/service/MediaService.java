package com.instagram.InstagramAnalyticsDashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import com.instagram.InstagramAnalyticsDashboard.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Value("${instagram.business.id}")
    private String businessId;

    @Value("${instagram.page.access.token}")
    private String accessToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

   public String getMedia() {

    try {

        String nextUrl =
                "https://graph.facebook.com/v23.0/"
                        + businessId
                        + "/media"
                        + "?fields=id,caption,media_type,media_product_type,media_url,permalink,thumbnail_url,timestamp"
                        + "&limit=100"
                        + "&access_token="
                        + accessToken;

        while (nextUrl != null) {

            String response = restTemplate.getForObject(nextUrl, String.class);

            System.out.println("========================");
            System.out.println(response);
            System.out.println("========================");

            JsonNode root = objectMapper.readTree(response);
            JsonNode data = root.path("data");

            if (data.isArray()) {

                for (JsonNode node : data) {

                    String mediaId = node.path("id").asText();

                    Optional<Media> optional =
                            mediaRepository.findByMediaId(mediaId);

                    Media media = optional.orElse(new Media());

                    media.setMediaId(mediaId);
                    media.setCaption(node.path("caption").asText(""));

                    String mediaType = node.path("media_type").asText("");
                    String productType = node.path("media_product_type").asText("");

                    if ("REELS".equalsIgnoreCase(productType)) {
                        media.setMediaType("REEL");
                    } else {
                        media.setMediaType(mediaType);
                    }

                    media.setMediaUrl(node.path("media_url").asText(""));
                    media.setPermalink(node.path("permalink").asText(""));
                    media.setThumbnailUrl(node.path("thumbnail_url").asText(""));
                    media.setTimestamp(node.path("timestamp").asText(""));

                    mediaRepository.save(media);
                }
            }

            JsonNode paging = root.path("paging");

            if (paging.has("next")) {
                nextUrl = paging.get("next").asText();
            } else {
                nextUrl = null;
            }
        }

        return "Media Saved Successfully";

    } catch (Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
        public String saveMedia() {
        return getMedia();
    }

    public List<Media> searchByCaption(String caption) {
        return mediaRepository.findByCaptionContainingIgnoreCase(caption);
    }

public String updateAnalytics() {

    try {

        List<Media> mediaList = mediaRepository.findAll();

        for (Media media : mediaList) {

            // ===============================
            // Likes & Comments
            // ===============================
            try {

                String url =
                        "https://graph.facebook.com/v23.0/"
                                + media.getMediaId()
                                + "?fields=like_count,comments_count"
                                + "&access_token="
                                + accessToken;

                String response = restTemplate.getForObject(url, String.class);
                JsonNode json = objectMapper.readTree(response);

                if (json.has("like_count")) {
                    media.setLikeCount(json.get("like_count").asInt());
                }

                if (json.has("comments_count")) {
                    media.setCommentsCount(json.get("comments_count").asInt());
                }

            } catch (Exception e) {

                System.out.println("Skipping Likes/Comments for Media ID: "
                        + media.getMediaId());

                media.setLikeCount(0);
                media.setCommentsCount(0);
            }

            // ===============================
            // Views (Reels / Videos)
            // ===============================
            if ("VIDEO".equalsIgnoreCase(media.getMediaType())
                    || "REEL".equalsIgnoreCase(media.getMediaType())) {

                try {

                    String insightsUrl =
                            "https://graph.facebook.com/v23.0/"
                                    + media.getMediaId()
                                    + "/insights?metric=views"
                                    + "&access_token="
                                    + accessToken;

                    String insightsResponse =
                            restTemplate.getForObject(insightsUrl, String.class);

                    JsonNode insightsJson =
                            objectMapper.readTree(insightsResponse);

                    JsonNode data = insightsJson.path("data");

                    if (data.isArray() && data.size() > 0) {

                        JsonNode values = data.get(0).path("values");

                        if (values.isArray() && values.size() > 0) {
                            media.setViews(values.get(0).path("value").asInt());
                        } else {
                            media.setViews(0);
                        }

                    } else {
                        media.setViews(0);
                    }

                } catch (Exception e) {

                    System.out.println("Skipping Views for Media ID: "
                            + media.getMediaId());

                    media.setViews(0);
                }
            } else {
                media.setViews(0);
            }

            mediaRepository.save(media);
        }

        return "Analytics Updated Successfully";

    } catch (Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}

        public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public Optional<Media> getMediaById(Long id) {
        return mediaRepository.findById(id);
    }

    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }

    public long getTotalMediaCount() {
        return mediaRepository.count();
    }

    public int getTotalLikes() {
        return mediaRepository.findAll()
                .stream()
                .mapToInt(Media::getLikeCount)
                .sum();
    }

    public int getTotalComments() {
        return mediaRepository.findAll()
                .stream()
                .mapToInt(Media::getCommentsCount)
                .sum();
    }

    public int getTotalViews() {
        return mediaRepository.findAll()
                .stream()
                .mapToInt(Media::getViews)
                .sum();
    }
    public Optional<Media> getMostViewedReel() {
    return mediaRepository.findTopByOrderByViewsDesc();
}


}
