package main.java.com.model;

public class Product {
	private final Double price;
	private String name;

	public Product(String name) {
		this.price = setPice(name);
		this.name = name;
	}

	private double setPice( final String item) {
		String product = item.toLowerCase();
		double itemPrice = 0.0;
		switch (product) {
			case "cola":
				itemPrice =   1;	break;
			case "chips":
				itemPrice =  0.5; 	break;
			case "candy":
				itemPrice =  0.65;	break;
			default:
				break;
		}
 
		return itemPrice;
	}

	public Double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
