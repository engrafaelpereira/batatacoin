package com.batatacoin.business.model;

import java.math.BigDecimal;

import com.batatacoin.business.enums.TipoMoedaEnum;

public class WalletFake implements Wallet {
	
	private BigDecimal valor;
	private TipoMoedaEnum moedaAtual;

	public WalletFake() {
		super();
	}
	
	public WalletFake(BigDecimal valor, TipoMoedaEnum moedaAtual) {
		super();
		this.valor = valor;
		this.moedaAtual = moedaAtual;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setMoedaAtual(TipoMoedaEnum moedaAtual) {
		this.moedaAtual = moedaAtual;
	}

	@Override
	public BigDecimal getValor() {
		return this.valor;
	}

	@Override
	public TipoMoedaEnum getMoedaAtual() {
		return this.moedaAtual;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private BigDecimal valor;
		private TipoMoedaEnum moedaAtual;
		
		public WalletFake build() {
			return new WalletFake(valor, moedaAtual);
		}
		
		public Builder valor(BigDecimal value) {
			valor = value;
			return this;
		}
		
		public Builder moedaAtual(TipoMoedaEnum value) {
			moedaAtual = value;
			return this;
		}
	
	}
}
