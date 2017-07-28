package com.gs.campaign.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gs.campaign.domain.Campaign;

public class CampaignDateProcessorTest {

    @Test
    public void process() {
	
	Campaign c1 = new Campaign(1l, "C1", "T1", LocalDate.parse("2017-10-01"), LocalDate.parse("2017-10-03"));
	Campaign c2 = new Campaign(2l, "C2", "T2", LocalDate.parse("2017-10-01"), LocalDate.parse("2017-10-02"));
	Campaign c3 = new Campaign(3l, "C3", "T3", LocalDate.parse("2017-10-01"), LocalDate.parse("2017-10-03"));
	
	List<Campaign> campaigns = new ArrayList<Campaign>();
	campaigns.add(c1);
	campaigns.add(c2);
	
	CampaignDateProcessor.process(campaigns, c3);
	assertEquals(LocalDate.parse("2017-10-04"), campaigns.get(0).getEndDate());
	assertEquals(LocalDate.parse("2017-10-05"), campaigns.get(1).getEndDate());
	assertEquals(LocalDate.parse("2017-10-03"), campaigns.get(2).getEndDate());
    }

}
