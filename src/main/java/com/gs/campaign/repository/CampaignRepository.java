package com.gs.campaign.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.gs.campaign.domain.Campaign;

public interface CampaignRepository extends Repository<Campaign, Long> {
    
    Optional<Campaign> findByIdAndEndDateGreaterThanEqual(Long id, LocalDate today);
    
    Campaign save(Campaign campaign);
    
    void delete(Long id);
    
    List<Campaign> findByEndDateGreaterThanEqual(LocalDate today);

}
