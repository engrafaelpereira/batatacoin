package com.batatacoin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.batatacoin.entity.Ticker;
import com.batatacoin.entity.mercadobitcoin.MBTicker;

@Service
public class TickerServiceImpl implements TickerService {

	public Ticker getTicker() {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://www.mercadobitcoin.net/api/btc/Ticker/";
		ResponseEntity<MBTicker> response = restTemplate.getForEntity(fooResourceUrl, MBTicker.class);
		return response.getBody().getTicker();
	}
}
