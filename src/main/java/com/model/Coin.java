package main.java.com.model;

public class Coin {

private CoinType type;
	
	public Coin(final CoinType coinType){
		this.type = coinType;
	}

	public CoinType getType() {
		return type;
	}

}
