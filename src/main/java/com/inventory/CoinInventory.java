package main.java.com.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.model.CoinType;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;

public class CoinInventory {

	private Map<CoinType, List<CoinType>> coinMap = new HashMap<CoinType, List<CoinType>>();

	public void load(final List<CoinType> coins) {
		for (final CoinType coin : coins) {
			load(coin);
		}
	}

	public void load(final CoinType coin) {
		List<CoinType> coins = coinMap.get(coin);
		if (null == coins)
			coins = new ArrayList<CoinType>();

		coins.add(coin);
		coinMap.put(coin, coins);
	}

	/*
	 * public void useCoin(final double amount ) { int moneyConsumed = 0; for
	 * (Map.Entry<CoinType, List<CoinType>> coinEntry : coinMap.entrySet()) {
	 * 
	 * } }
	 */
	public void emptyCoinInventory() {
		coinMap.clear();
	}

	public int getCountOfInventoryCoins() {
		return null == coinMap ? 0 : coinMap.size();
	}

	public int getCountByCoinType(final CoinType coinType)  {
		final List<CoinType> coins = coinMap.get(coinType);
        
		if(coins == null )
        	return 0;
		
		return getCountOfInventoryCoins();
	}

	public Map<CoinType, List<CoinType>> getCoinsCollected() {
		return coinMap;
	}

	public int getCountByOnesEquivalent() throws CoinTypeNotAvailableException {
		int sumOfMoney = 0;

		if (0 == getCountOfInventoryCoins())
			throw new CoinTypeNotAvailableException(" No Coins Detected");

		for (Map.Entry<CoinType, List<CoinType>> coinEntry : coinMap.entrySet()) {
			sumOfMoney += convertCoinTypeToIntValue(coinEntry.getKey(), coinEntry.getValue().size());
		}

		return sumOfMoney;
	}

	private int convertCoinTypeToIntValue(CoinType key, int size) {
		int coinSum = 0;

		switch (key) {
		case NICKEL:
			coinSum = 5 * size;
			break;
		case DIME:
			coinSum = 10 * size;
			break;
		case QUARTER:
			coinSum = 25 * size;
			break;

		default:
			break;
		}

		return coinSum;
	}

	/**
	 * 
	 * @param S
	 *            allowed coinTypes' value
	 * @param m
	 *            number of coinTypes
	 * @param n
	 *            Amount whose change equivalent is being found
	 * @return
	 *
	 */
	public int countCoinChangeWays(int S[], int m, int n) {

		int[] table = new int[n + 1];
		Arrays.fill(table, 0);
		table[0] = 1;

		for (int i = 0; i < m; i++)
			for (int j = S[i]; j <= n; j++)
				table[j] += table[j - S[i]];

		return table[n];
	}

	/**
	 * @throws CoinTypeNotAvailableException 
	 * 
	 *
	 */
	public boolean hasCoinChange( Product product) throws CoinTypeNotAvailableException {
		
		int coinsInserted      = getCountByOnesEquivalent();
		Double productPrice    = product.getPrice();
		Double productPriceOnesEquivalent =  productPrice * 100;
		int val = productPriceOnesEquivalent.intValue();
				
		if( productPriceOnesEquivalent  > coinsInserted )
			return true;
		
	    return false;					
	}

}
