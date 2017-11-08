package com.batatacoin.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batatacoin.service.TickerService;


@Component
public class ScheduledTasks {
	
	@Autowired
	private TickerService tickerService;

    private static final int SPLEEP_TIME = 1000;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = SPLEEP_TIME)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info(tickerService.getTicker());
    }
}