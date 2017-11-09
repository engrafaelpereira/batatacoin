package com.batatacoin.business.model;

import java.math.BigDecimal;

import com.batatacoin.business.enums.TipoMoedaEnum;

public interface Wallet {
	
	public BigDecimal getValor();
	
	public TipoMoedaEnum getMoedaAtual();

}
