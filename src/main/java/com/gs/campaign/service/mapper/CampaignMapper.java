package com.gs.campaign.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.gs.campaign.domain.Campaign;
import com.gs.campaign.service.dto.CampaignDTO;

public class CampaignMapper {

    public static CampaignDTO campaignToCampaignDTO(Campaign campaign) {
        return new CampaignDTO(campaign);
    }
    
    public static Campaign campaignDTOToCampaign(CampaignDTO campaignDTO) {
	if (campaignDTO == null) {
	    return null;
	}
	
	return new Campaign(campaignDTO.getId(), campaignDTO.getName(), campaignDTO.getHeartTeam(), 
		campaignDTO.getStartDate(), campaignDTO.getEndDate());
    }
    
    public static List<CampaignDTO> campaignsToCampaignDTOs(List<Campaign> campaigns) {
        return campaigns.stream()
            .filter(Objects::nonNull)
            .map(campaign -> CampaignMapper.campaignToCampaignDTO(campaign))
            .collect(Collectors.toList());
    }

}
