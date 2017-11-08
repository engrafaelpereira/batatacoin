package com.batatacoin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TickerServiceImpl implements TickerService {

	public String getTicker() {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://www.mercadobitcoin.net/api/btc/ticker/";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		return response.getBody();
	}
}
