package com.instagram.InstagramAnalyticsDashboard.repository;

import com.instagram.InstagramAnalyticsDashboard.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByMediaId(String mediaId);

    List<Media> findByCaptionContainingIgnoreCase(String caption);
    Optional<Media> findTopByOrderByViewsDesc();

}