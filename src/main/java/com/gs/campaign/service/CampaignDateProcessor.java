package com.gs.campaign.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gs.campaign.domain.Campaign;

public class CampaignDateProcessor {

    public CampaignDateProcessor() {
    }

    public static void process(List<Campaign> campaigns, Campaign campaignToSave) {

	List<Campaign> sortedCampaigns = campaigns.stream()
		.sorted((c1, c2) -> c1.getEndDate().compareTo(c2.getEndDate()))
		.collect(Collectors.toList());

	plusDays(campaignToSave.getStartDate(), campaignToSave.getEndDate(), sortedCampaigns, 1);

	campaigns.add(campaignToSave);
	sortedCampaigns.add(campaignToSave);

	sortedCampaigns.forEach(campaign -> {
	    List<Campaign> copyOfCampaigns = new ArrayList<Campaign>();
	    copyOfCampaigns.addAll(sortedCampaigns);
	    copyOfCampaigns.remove(campaign);

	    if (existsDate(campaign.getEndDate(), copyOfCampaigns)) {
		campaign.addDays(1);
	    }
	});

    }

    private static boolean existsDate(LocalDate date, List<Campaign> campaigns) {
	return campaigns.stream().anyMatch(campaign -> date.isEqual(campaign.getEndDate()));
    }

    private static void plusDays(LocalDate startDate, LocalDate endDate, List<Campaign> campaigns, int days) {
	campaigns.stream().filter(c -> ((c.getEndDate().isAfter(startDate) && c.getEndDate().isBefore(endDate))
		|| c.getEndDate().isEqual(endDate))).forEach(c -> {
		    c.addDays(days);
		});
    }

}
