package com.batatacoin.entity.mercadobitcoin;

import java.math.BigDecimal;
import java.util.Date;

import com.batatacoin.entity.Ticker;

public class MBChildTicker implements Ticker {

	public BigDecimal high;
	
	public BigDecimal low;
	
	public BigDecimal vol;
	
	public BigDecimal last;

	public BigDecimal sell;
	
	public BigDecimal buy;
	
	public int date;

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getVol() {
		return vol;
	}

	public void setVol(BigDecimal vol) {
		this.vol = vol;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public BigDecimal getSell() {
		return sell;
	}

	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}

	public BigDecimal getBuy() {
		return buy;
	}

	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		Date convertedDate = new Date(date * 1000L);
		String txt = convertedDate.toLocaleString();
				
		return "MBChildTicker [last=" + last + ", sell=" + sell + ", buy=" + buy + ", date=" + txt + "]";
	}
}
