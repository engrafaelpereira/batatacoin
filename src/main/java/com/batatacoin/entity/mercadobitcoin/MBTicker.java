package com.batatacoin.entity.mercadobitcoin;

public class MBTicker {
	public Ticker ticker;

	public Ticker getTicker() {
		return ticker;
	}

	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}

	@Override
	public String toString() {
		return "MBTicker [ticker=" + ticker + "]";
	}
	
	
}
