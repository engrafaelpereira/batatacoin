package com.batatacoin.scheduler;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.batatacoin.business.enums.EstruturaWalletFake;
import com.batatacoin.business.enums.TipoMoedaEnum;
import com.batatacoin.business.model.Wallet;
import com.batatacoin.business.model.WalletFake;
import com.batatacoin.entity.mercadobitcoin.Ticker;
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

	@Value("${percentual.lucro}")
	private BigDecimal percentual;

	@Value("${path.wallet.fake}")
	private String pathWalletFake;

	@Value("${nome.wallet.fake}")
	private String nomeWalletFake;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = SLEEP_TIME)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		Wallet carteira = montarWalletFake();
		if (carteira.getMoedaAtual() == TipoMoedaEnum.BLR) {
			comprarBitcoin(carteira, tickerService.getTicker().getTicker());
		} else {
			venderBitcoin(carteira, tickerService.getTicker().getTicker());
		}
		log.info(tickerService.getTicker().getTicker().toString());
	}

	private Wallet montarWalletFake() {
		String ultimaTransacao = FileSystem.lerUltimaLinha(pathWalletFake, nomeWalletFake);
		if (!StringUtils.isEmpty(ultimaTransacao)) {
			return WalletFake.builder()
					.valor(new BigDecimal(ultimaTransacao.substring(EstruturaWalletFake.VALOR.getPosInicial(),
							EstruturaWalletFake.VALOR.getPosFinal())))
					.moedaAtual(
							TipoMoedaEnum.fromMoeda(ultimaTransacao.substring(EstruturaWalletFake.MOEDA.getPosInicial(),
									EstruturaWalletFake.MOEDA.getPosFinal())))
					.build();
		}

		return null;
	}
	
	private void comprarBitcoin(Wallet carteira, Ticker ticker) {
		
	}
	
	private void venderBitcoin(Wallet carteira, Ticker ticker) {
		
	}
}