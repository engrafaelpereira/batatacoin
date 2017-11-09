package com.batatacoin.scheduler;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batatacoin.business.enums.EstruturaWalletFake;
import com.batatacoin.business.enums.TipoMoedaEnum;
import com.batatacoin.business.model.Wallet;
import com.batatacoin.business.model.WalletFake;
import com.batatacoin.entity.Ticker;
import com.batatacoin.service.TickerService;
import com.batatacoin.utils.FileSystem;

@Component
public class ScheduledTasks {

	@Autowired
	private TickerService tickerService;

	private static final int REFRESH_TIME = 1000;

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

	@Value("${path.ultimo.valor}")
	private String pathUltimoValor;

	@Value("${nome.ultimo.valor}")
	private String nomeUltimoValor;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = REFRESH_TIME)
	public void reportTicker() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		Wallet carteira = montarWalletFake();
		if (carteira.getMoedaAtual() == TipoMoedaEnum.BLR) {
			comprarBitcoin(carteira, tickerService.getTicker());
		} else {
			venderBitcoin(carteira, tickerService.getTicker());
		}
		log.info(tickerService.getTicker().toString());
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
		BigDecimal valorUltimaTransacao = new BigDecimal(FileSystem.lerUltimaLinha(pathUltimoValor, nomeUltimoValor));
		BigDecimal valorCompra = valorUltimaTransacao.subtract(valorUltimaTransacao.multiply(percentual));
		if (ticker.getLast().compareTo(valorCompra) <= 0) {
			FileSystem.salvar(path, nomeArquivo,
					String.format("Comprando Bitcoins, pelo valor %f . %f Bitcoints comprados", ticker.getLast(),
							carteira.getValor().divide(ticker.getLast())));
			FileSystem.salvar(pathWalletFake, nomeWalletFake, String.format("%s%s", TipoMoedaEnum.BTC.getMoeda(),
					StringUtils.leftPad(carteira.getValor().divide(ticker.getLast()).toString(), 22, '0')));
			FileSystem.salvar(pathUltimoValor, nomeUltimoValor, ticker.getLast().toString());
		}
	}

	private void venderBitcoin(Wallet carteira, Ticker ticker) {
		BigDecimal valorUltimaTransacao = new BigDecimal(FileSystem.lerUltimaLinha(pathUltimoValor, nomeUltimoValor));
		BigDecimal valorVenda = valorUltimaTransacao.add(valorUltimaTransacao.multiply(percentual));
		if (ticker.getLast().compareTo(valorVenda) == 1) {
			FileSystem.salvar(path, nomeArquivo, String.format(
					"Vendendo Bitcoins, pelo valor %f . %f Bitcoints vendidos", ticker.getLast(), carteira.getValor()));
			FileSystem.salvar(pathWalletFake, nomeWalletFake, String.format("%s%s", TipoMoedaEnum.BLR.getMoeda(),
					StringUtils.leftPad(carteira.getValor().multiply(ticker.getLast()).toString(), 22, '0')));
			FileSystem.salvar(pathUltimoValor, nomeUltimoValor, ticker.getLast().toString());
		}
	}
}