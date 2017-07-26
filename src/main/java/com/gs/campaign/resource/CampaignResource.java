package com.gs.campaign.resource;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO, HttpServletRequest request) {
	if (campaignDTO.getId() != null) {
	    throw new ConstraintViolationException("A new Campaign cannot already have an ID", null);
	} 
	
	CampaignDTO savedCampaign = campaignService.save(campaignDTO);
	HttpHeaders header = new HttpHeaders();
	header.set("Location", request.getRequestURL() + "/" + savedCampaign.getId());
	return ResponseEntity.status(HttpStatus.CREATED).headers(header).body(savedCampaign);
	
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CampaignDTO> getCampaign(@PathVariable Long id) {
	Optional<CampaignDTO> campaignDTO = campaignService.findOne(id);
	if (!campaignDTO.isPresent()) {
	    throw new EntityNotFoundException("The requested resource was not found on this server");
	}
	return ResponseEntity.ok(campaignDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
	return ResponseEntity.ok().body(campaignService.findAll());
    }
    
    @PutMapping
    public ResponseEntity<CampaignDTO> updateCampaign(@RequestBody CampaignDTO campaignDTO) {
	if (campaignDTO.getId() == null) {
	   throw new NullPointerException("The resource's ID must to be given"); 
	}
	Optional<CampaignDTO> existingCampaign = campaignService.findOne(campaignDTO.getId());
	if (!existingCampaign.isPresent()) {
	    throw new EntityNotFoundException("The requested resource was not found on this server");
	}
	return ResponseEntity.ok(campaignService.update(campaignDTO));
	
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
	Optional<CampaignDTO> campaignDTO = campaignService.findOne(id);
	if (!campaignDTO.isPresent()) {
	    throw new EntityNotFoundException("The requested resource was not found on this server");
	}
	campaignService.delete(id);
	return ResponseEntity.ok().build();
    }

}
