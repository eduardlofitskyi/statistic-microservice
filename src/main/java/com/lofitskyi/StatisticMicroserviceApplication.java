package com.lofitskyi;

import com.lofitskyi.entity.Currency;
import com.lofitskyi.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SpringBootApplication
public class StatisticMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticMicroserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CurrencyRepository currencyRepository){
		return string -> Stream.of(	new Currency(1L, "Dollar", BigDecimal.valueOf(1)),
									new Currency(2L, "Yena", BigDecimal.valueOf(3.4)))
                .forEach(currencyRepository::save);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
