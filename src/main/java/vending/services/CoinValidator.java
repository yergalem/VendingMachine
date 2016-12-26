package main.java.vending.services;

import main.java.com.model.Coin;
import main.java.com.model.CoinType;

public class CoinValidator {

  public Boolean validate(final Coin coin){
		
		return CoinType.getValidCoinTypes().contains(coin.getType());
  }
}
