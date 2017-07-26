package com.gs.campaign.service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.campaign.domain.Campaign;


public class CampaignDTO {

    private Long id;

    private String name;

    private String heartTeam;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate endDate;
    
    public CampaignDTO() {};
    
    public CampaignDTO(Campaign campaign) {
	this.id = campaign.getId();
	this.name = campaign.getName();
	this.heartTeam = campaign.getHeartTeam();
	this.startDate = campaign.getStartDate();
	this.endDate = campaign.getEndDate();
    }

    public CampaignDTO(String name, String heartTeam, LocalDate startDate, LocalDate endDate) {
	this.name = name;
	this.heartTeam = heartTeam;
	this.startDate = startDate;
	this.endDate = endDate;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeartTeam() {
        return heartTeam;
    }

    public void setHeartTeam(String heartTeam) {
        this.heartTeam = heartTeam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
	return "CampaignDTO [id=" + id + ", name=" + name + ", heartTeam=" + heartTeam + ", startDate=" + startDate
		+ ", endDate=" + endDate + "]";
    }

}
