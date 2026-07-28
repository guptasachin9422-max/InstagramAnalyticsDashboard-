package com.instagram.InstagramAnalyticsDashboard.repository;

import com.instagram.InstagramAnalyticsDashboard.entity.FacebookPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacebookRepository extends JpaRepository<FacebookPage, String> {

    FacebookPage findByPageName(String pageName);

}