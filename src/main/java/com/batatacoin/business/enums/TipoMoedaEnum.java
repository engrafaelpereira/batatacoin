package com.batatacoin.business.enums;

public enum TipoMoedaEnum {

	BTC(1, "BTC"), BLR(2, "BLR");

	private int id;

	private String moeda;

	private TipoMoedaEnum(int id, String moeda) {
		this.id = id;
		this.moeda = moeda;
	}

	public String getMoeda() {
		return moeda;
	}

	public int getId() {
		return id;
	}

	public static TipoMoedaEnum fromMoeda(String moeda) {
		for (TipoMoedaEnum tipoMoeda : TipoMoedaEnum.values()) {
			if (tipoMoeda.moeda.equalsIgnoreCase(moeda)) {
				return tipoMoeda;
			}
		}
		return null;
	}
}
