package com.instagram.InstagramAnalyticsDashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Entity
@Table(name = "media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String mediaId;

    @Column(columnDefinition = "TEXT")
    private String caption;

    private String mediaType;

    @Column(columnDefinition = "TEXT")
    private String mediaUrl;

    @Column(columnDefinition = "TEXT")
    private String permalink;

    @Column(columnDefinition = "TEXT")
    private String thumbnailUrl;

    private String timestamp;

    private Integer likeCount = 0;
    private Integer commentsCount = 0;
    private Integer views = 0;
}