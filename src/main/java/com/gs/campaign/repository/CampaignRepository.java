package com.gs.campaign.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.campaign.domain.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
    Optional<Campaign> findByIdAndEndDateGreaterThanEqual(Long id, LocalDate today);
    
    List<Campaign> findByEndDateGreaterThanEqual(LocalDate today);
    
    List<Campaign> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

}