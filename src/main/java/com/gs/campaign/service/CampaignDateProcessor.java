package com.gs.campaign.service;

import java.time.LocalDate;
import java.util.List;

import com.gs.campaign.domain.Campaign;

public class CampaignDateProcessor {

    public CampaignDateProcessor() {
    }

    public static void process(List<Campaign> campaigns, Campaign campaignToSave) {

	plusDays(campaignToSave.getStartDate(), campaignToSave.getEndDate(), campaigns, 1);
	campaigns.add(campaignToSave);

	campaigns.forEach(campaign -> {
	    while (existsDate(campaign, campaigns)) {
		campaign.addDays(1);
	    }
	});
    }

    private static boolean existsDate(Campaign campaign, List<Campaign> campaigns) {
	return campaigns.stream()
		.filter(c -> !c.equals(campaign))
		.anyMatch(c -> c.getEndDate().isEqual(campaign.getEndDate()));
    }

    private static void plusDays(LocalDate startDate, LocalDate endDate, List<Campaign> campaigns, int days) {
	campaigns.stream().filter(c -> ((c.getEndDate().isAfter(startDate) && c.getEndDate().isBefore(endDate))
		|| c.getEndDate().isEqual(endDate))).forEach(c -> {
		    c.addDays(days);
		});
    }

}
