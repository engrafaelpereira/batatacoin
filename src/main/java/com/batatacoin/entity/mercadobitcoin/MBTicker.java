package com.batatacoin.entity.mercadobitcoin;

public class MBTicker {
	public MBChildTicker mBChildTicker;

	public MBChildTicker getTicker() {
		return mBChildTicker;
	}

	public void setTicker(MBChildTicker mBChildTicker) {
		this.mBChildTicker = mBChildTicker;
	}

	@Override
	public String toString() {
		return "MBTicker [mBChildTicker=" + mBChildTicker + "]";
	}
	
	
}
