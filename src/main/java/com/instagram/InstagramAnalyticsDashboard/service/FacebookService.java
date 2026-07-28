package com.instagram.InstagramAnalyticsDashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPage;
import com.instagram.InstagramAnalyticsDashboard.repository.FacebookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookService {

    @Value("${facebook.page.id}")
    private String pageId;

    @Value("${facebook.page.access.token}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private final FacebookRepository repository;

    public FacebookService(FacebookRepository repository) {
        this.repository = repository;
    }

    public FacebookPage fetchPage() throws Exception {

        String url =
                "https://graph.facebook.com/v23.0/"
                        + pageId
                        + "?fields=id,name,followers_count,fan_count,picture"
                        + "&access_token="
                        + accessToken;

        String response = restTemplate.getForObject(url, String.class);

        JsonNode json = mapper.readTree(response);

        FacebookPage page = new FacebookPage();

        page.setPageId(json.path("id").asText());
        page.setPageName(json.path("name").asText());
        page.setFollowers(json.path("followers_count").asInt());
        page.setLikes(json.path("fan_count").asInt());

        if (json.has("picture")) {
            page.setPagePicture(
                    json.path("picture")
                            .path("data")
                            .path("url")
                            .asText());
        }

        repository.save(page);

        return page;
    }

    public String savePage() throws Exception {
        fetchPage();
        return "Facebook Page Saved Successfully";
    }

}