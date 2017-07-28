package com.gs.campaign.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CampaignTest {

    @Test
    public void addDays() {
	Campaign campaign = new Campaign("C1", "Team1", LocalDate.parse("2017-05-05"), LocalDate.parse("2017-05-06"));
	campaign.addDays(1);
	LocalDate expectedDate = LocalDate.parse("2017-05-07");
	assertEquals(expectedDate, campaign.getEndDate());
    }

}
