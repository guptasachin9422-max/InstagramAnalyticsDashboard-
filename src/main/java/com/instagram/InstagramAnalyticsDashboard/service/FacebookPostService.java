package com.instagram.InstagramAnalyticsDashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPost;
import com.instagram.InstagramAnalyticsDashboard.repository.FacebookPostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookPostService {

    @Value("${facebook.page.id}")
    private String pageId;

    @Value("${facebook.page.access.token}")
    private String accessToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final FacebookPostRepository repository;

    public FacebookPostService(RestTemplate restTemplate,
                               ObjectMapper objectMapper,
                               FacebookPostRepository repository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    public List<FacebookPost> fetchPosts() throws Exception {

        String url = "https://graph.facebook.com/v23.0/"
        + pageId
        + "/posts?fields=id,message,created_time"
        + "&access_token="
        + accessToken;

        String response = restTemplate.getForObject(url, String.class);

        JsonNode root = objectMapper.readTree(response);

        List<FacebookPost> posts = new ArrayList<>();

        if (root.has("data")) {

            for (JsonNode node : root.get("data")) {

                FacebookPost post = new FacebookPost();


                post.setPostId(node.path("id").asText());
                post.setMessage(node.path("message").asText(""));
                post.setCreatedTime(node.path("created_time").asText(""));

                posts.add(post);

                // post.setReactions(
                //         node.path("reactions")
                //                 .path("summary")
                //                 .path("total_count")
                //                 .asInt(0));

                // post.setComments(
                //         node.path("comments")
                //                 .path("summary")
                //                 .path("total_count")
                //                 .asInt(0));

                // post.setShares(
                //         node.path("shares")
                //                 .path("count")
                //                 .asInt(0));

                // posts.add(post);
            }
        }

        return posts;
    }

    public String savePosts() throws Exception {

        List<FacebookPost> posts = fetchPosts();

        repository.saveAll(posts);

        return "Facebook Posts Saved Successfully";
    }

    public List<FacebookPost> getAllPosts() {

        return repository.findAll();
    }

}