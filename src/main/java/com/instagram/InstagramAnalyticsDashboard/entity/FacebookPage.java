package com.instagram.InstagramAnalyticsDashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facebook_page")
@Data
public class FacebookPage {

    @Id
    private String pageId;

    private String pageName;

    private Integer followers;

    private Integer likes;
    

    @Column(length = 3000)
    private String pagePicture;

}