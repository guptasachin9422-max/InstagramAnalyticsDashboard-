package com.instagram.InstagramAnalyticsDashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instagram_accounts")
@Data
public class InstagramAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String instagramId;

    private String username;

    @Column(length = 3000)
    private String biography;

    private Integer followersCount;

    private Integer followingCount;

    private Integer postCount;

    @Column(length = 5000)
    private String profilePicture;

}