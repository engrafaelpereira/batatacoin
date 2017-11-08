package com.batatacoin.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batatacoin.service.TickerService;
import com.batatacoin.utils.FileSystem;


@Component
public class ScheduledTasks {
	
	@Autowired
	private TickerService tickerService;

    private static final int SLEEP_TIME = 1000;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Value("${path.filesystem.log}")
	private String path;
	
	@Value("${filesystem.nomearquivo}")
	private String nomeArquivo;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Scheduled(fixedRate = SLEEP_TIME)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        FileSystem.salvar(path, nomeArquivo, "teste");
        log.info(tickerService.getTicker().getTicker().toString());
    }
}