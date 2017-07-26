package com.gs.campaign.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.gs.campaign.domain.Campaign;

public interface CampaignRepository extends Repository<Campaign, Long> {
    
    Optional<Campaign> findOne(Long id);
    
    Campaign save(Campaign campaign);
    
    void delete(Long id);
    
    @Query("select c from Campaign c where c.endDate >= ?#{[0]}")
    List<Campaign> findAll(LocalDate date);

}
