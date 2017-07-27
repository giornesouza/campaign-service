package com.gs.campaign.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.campaign.exception.RequiredParamException;
import com.gs.campaign.exception.UnexpectedParamException;
import com.gs.campaign.resource.constants.ResourceMessageConstant;
import com.gs.campaign.service.CampaignService;
import com.gs.campaign.service.dto.CampaignDTO;

@RestController
@RequestMapping(path = "/api/campaigns", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CampaignResource {

    private final CampaignService campaignService;

    public CampaignResource(CampaignService campaignService) {
	this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> createCampaign(@Valid @RequestBody CampaignDTO campaignDTO,
	    HttpServletRequest request) throws URISyntaxException {
	if (campaignDTO.getId() != null) {
	    throw new UnexpectedParamException(ResourceMessageConstant.UNEXPECTED_ID_PARAM);
	} 	
	CampaignDTO savedCampaign = campaignService.save(campaignDTO);	
	URI location = new URI(request.getRequestURL() + "/" + savedCampaign.getId());
	return ResponseEntity.created(location).body(savedCampaign);
	
    }

    @GetMapping(path = "/{id:[0-9]+}")
    public ResponseEntity<CampaignDTO> getCampaign(@PathVariable Long id) {
	Optional<CampaignDTO> campaignDTO = campaignService.findOne(id);
	if (!campaignDTO.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	return ResponseEntity.ok(campaignDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
	return ResponseEntity.ok().body(campaignService.findAll());
    }
    
    @PutMapping
    public ResponseEntity<CampaignDTO> updateCampaign(@Valid @RequestBody CampaignDTO campaignDTO) {
	if (campaignDTO.getId() == null) {
	   throw new RequiredParamException(ResourceMessageConstant.REQUIRED_ID_PARAM); 
	}
	Optional<CampaignDTO> existingCampaign = campaignService.findOne(campaignDTO.getId());
	if (!existingCampaign.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	return ResponseEntity.ok(campaignService.update(campaignDTO));
	
    }
    
    @DeleteMapping(path = "/{id:[0-9]+}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
	Optional<CampaignDTO> campaignDTO = campaignService.findOne(id);
	if (!campaignDTO.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	campaignService.delete(id);
	return ResponseEntity.ok().build();
    }

}
