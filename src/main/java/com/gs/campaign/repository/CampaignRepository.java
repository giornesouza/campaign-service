package com.gs.campaign.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.gs.campaign.domain.Campaign;

public interface CampaignRepository extends Repository<Campaign, Long> {
    
    Optional<Campaign> findOne(Long id);
    
    List<Campaign> findAll();
    
    Campaign save(Campaign campaign);
    
    void delete(Long id);

}
