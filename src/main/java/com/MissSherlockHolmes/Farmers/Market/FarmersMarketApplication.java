package com.MissSherlockHolmes.Farmers.Market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories({"com.MissSherlockHolmes.Farmers.Market.repository", "com.MissSherlockHolmes.Farmers.Market.service"})

public class FarmersMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(FarmersMarketApplication.class, args);
	}
}