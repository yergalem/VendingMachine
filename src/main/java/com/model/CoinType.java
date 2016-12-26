package main.java.com.model;

import java.util.ArrayList;
import java.util.List;

public enum CoinType {
	PENNEY(0.01), NICKEL(0.05), DIME(0.10), QUARTER(0.25);

	private double worth;

	CoinType(final double val) {
		worth = val;
	}

	public Double getWorth() {
		return worth;
	}

	private static List<CoinType> validCoinTypes = new ArrayList<CoinType>();

	static {
		validCoinTypes.add(NICKEL);
		validCoinTypes.add(DIME);
		validCoinTypes.add(QUARTER);
	}

	public static List<CoinType> getValidCoinTypes() {
		return validCoinTypes;
	}

}
