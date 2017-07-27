package com.gs.campaign;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gs.campaign.repository.CampaignRepository;
import com.gs.campaign.service.CampaignService;

@SpringBootApplication
public class CampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignApplication.class, args);
		
		
	}
	
	@Bean
	public CommandLineRunner demo(CampaignRepository repository, CampaignService service) {
		return (args) -> {
//		    	Campaign c1 = new Campaign("C1", "T1", LocalDate.parse("2017-10-01"), LocalDate.parse("2017-10-03"));
//			Campaign c2 = new Campaign("C2", "T2", LocalDate.parse("2017-10-01"), LocalDate.parse("2017-10-02"));
//			Campaign c4 = new Campaign("C4", "T4", LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-05"));
//			Campaign c5 = new Campaign("C5", "T5", LocalDate.parse("2017-01-01"), LocalDate.parse("2017-10-03"));
//			Campaign c6 = new Campaign("C6", "T6", LocalDate.parse("2017-12-01"), LocalDate.parse("2017-12-31"));
//			repository.save(c1);
//			repository.save(c2);
//			repository.save(c4);
//			repository.save(c6);
//			List<Campaign> list = repository.findByEndDateGreaterThanEqual(LocalDate.now());
//			CampaignDateProcessor.process(list, c5);
//			list.forEach(c -> {
//			    System.out.println(c.toString());
//			});
		};
	}
}
