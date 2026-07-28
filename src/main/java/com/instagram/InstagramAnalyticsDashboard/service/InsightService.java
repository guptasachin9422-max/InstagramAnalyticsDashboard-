package com.instagram.InstagramAnalyticsDashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.InstagramAnalyticsDashboard.entity.Insight;
import com.instagram.InstagramAnalyticsDashboard.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InsightService {

    @Autowired
    private InsightRepository insightRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${instagram.business.id}")
    private String businessId;

    @Value("${instagram.page.access.token}")
    private String accessToken;

    private final ObjectMapper mapper = new ObjectMapper();

    public String getInsights() {

        String url = "https://graph.facebook.com/v23.0/"
                + businessId
                + "/insights"
                + "?metric=reach,impressions,profile_views"
                + "&period=day"
                + "&access_token="
                + accessToken;

        return restTemplate.getForObject(url, String.class);
    }

    public String saveInsights() throws Exception {

        String response = getInsights();

        JsonNode root = mapper.readTree(response);

        JsonNode data = root.get("data");

        if (data != null) {

            for (JsonNode metricNode : data) {

                String metric = metricNode.path("name").asText();
                String period = metricNode.path("period").asText();

                JsonNode values = metricNode.path("values");

                if (values.isArray()) {

                    for (JsonNode valueNode : values) {

                        Insight insight = new Insight();

                        insight.setMetric(metric);
                        insight.setPeriod(period);
                        insight.setValue(valueNode.path("value").asText());
                        insight.setEndTime(valueNode.path("end_time").asText());

                        insightRepository.save(insight);
                    }
                }
            }
        }

        return "Insights Saved Successfully";
    }

}