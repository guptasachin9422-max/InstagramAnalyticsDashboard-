package com.instagram.InstagramAnalyticsDashboard.repository;

import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacebookPostRepository extends JpaRepository<FacebookPost, String> {

}