package com.instagram.InstagramAnalyticsDashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facebook_posts")
@Data
public class FacebookPost {

    @Id
    private String postId;

    @Column(length = 5000)
    private String message;

    private String createdTime;

    @Column(length = 1000)
    private String permalink;

    private Integer reactions;

    private Integer comments;

    private Integer shares;
}