package com.instagram.InstagramAnalyticsDashboard.repository;

import com.instagram.InstagramAnalyticsDashboard.entity.InstagramAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstagramRepository extends JpaRepository<InstagramAccount, Long> {

    InstagramAccount findByInstagramId(String instagramId);

    InstagramAccount findByUsername(String username);

}