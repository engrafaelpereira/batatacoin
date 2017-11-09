package com.batatacoin.entity;

import java.math.BigDecimal;

public interface Ticker {
	 BigDecimal getHigh();
	 BigDecimal getLow();
	 BigDecimal getVol();
	 BigDecimal getLast();
	 BigDecimal getSell();
	 BigDecimal getBuy();
}
