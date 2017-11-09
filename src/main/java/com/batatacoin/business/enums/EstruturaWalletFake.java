package com.batatacoin.business.enums;

public enum EstruturaWalletFake {
	
	MOEDA(0, 3),
	VALOR(3, 16);
	
	private int posInicial;
	private int posFinal;

	EstruturaWalletFake(int posInicial, int posFinal) {
		this.posInicial = posInicial;
		this.posFinal = posFinal;
	}

	public int getPosInicial() {
		return this.posInicial;
	}

	public int getPosFinal() {
		return this.posFinal;
	}
}
