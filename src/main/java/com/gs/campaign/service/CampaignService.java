package com.gs.campaign.service;

import java.time.LocalDate;
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
	return campaignRepository.findByIdAndEndDateGreaterThanEqual(id, LocalDate.now())
		.flatMap(campaign -> Optional.of(new CampaignDTO(campaign)));
    }

    public List<CampaignDTO> findAll() {
	return CampaignMapper
		.campaignsToCampaignDTOs(campaignRepository.findByEndDateGreaterThanEqual(LocalDate.now()));
    }

    public CampaignDTO save(CampaignDTO campaignDTO) {
	List<Campaign> campaigns = campaignRepository.findByEndDateGreaterThanEqual(LocalDate.now());
	Campaign campaignToSave = CampaignMapper.campaignDTOToCampaign(campaignDTO);
	CampaignDateProcessor.process(campaigns, campaignToSave);
	campaignRepository.save(campaigns);
	return new CampaignDTO(campaignToSave);
    }

    public CampaignDTO update(CampaignDTO campaignDTO) {
	Campaign updatedCampaign = campaignRepository.save(CampaignMapper.campaignDTOToCampaign(campaignDTO));
	return new CampaignDTO(updatedCampaign);
    }

    public void delete(Long id) {
	campaignRepository.delete(id);
    }

    public List<Campaign> findByEndDateBetween(LocalDate startDate, LocalDate endDate) {
	return campaignRepository.findByEndDateBetween(startDate, endDate);
    }

    

}
