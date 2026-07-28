package com.instagram.InstagramAnalyticsDashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "insights")
@Data
public class Insight {

    @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metric;

    private String period;

    @Column(length = 500)
    private String value;

    private String endTime;

}