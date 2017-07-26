package com.gs.campaign.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gs.campaign.domain.Campaign;
import com.gs.campaign.repository.CampaignRepository;
import com.gs.campaign.service.dto.CampaignDTO;
import com.gs.campaign.service.mapper.CampaignMapper;

@Service
public class CampaignService {
    
    private final CampaignRepository campaignRepository;
    
    
    public CampaignService(CampaignRepository campaignRepository) {
	this.campaignRepository = campaignRepository;
    }
    
    public Optional<CampaignDTO> findOne(Long id) {
	return Optional.of(new CampaignDTO(campaignRepository.findOne(id).get()));
    }
    
    public List<CampaignDTO> findAll() {
	return CampaignMapper.campaignsToCampaignDTOs(campaignRepository.findAll());
    }
    
    public CampaignDTO save(CampaignDTO campaignDTO) {
	Campaign savedCampaign = campaignRepository.save(CampaignMapper.campaignDTOToCampaign(campaignDTO));
	campaignDTO.setId(savedCampaign.getId());
	return campaignDTO;
    }
    
    public CampaignDTO update(CampaignDTO campaignDTO) {
	Campaign updatedCampaign = campaignRepository.save(CampaignMapper.campaignDTOToCampaign(campaignDTO));
	return CampaignMapper.campaignToCampaignDTO(updatedCampaign);
    }
    
    public void delete(Long id) {
	campaignRepository.delete(id);
    }
    
}
