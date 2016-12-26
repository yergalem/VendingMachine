package main.java.com.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.model.Product;
import main.java.com.vending.exception.ProductOutOfStockException;

/**
 * 
 * @author Yergalem
 *
 */
public class ProductInventory {
	
	private Map< String, List<Product>> productMap;
		
	public ProductInventory(){
			productMap = new HashMap<String, List<Product>>();
	}
	
	public void load(final List<Product> products){
		for(final Product product : products){
			this.load(product);
		}
	}
	
	public void load(final Product product){
		List<Product> products = productMap.get(product.getName());
		if(null == products){
			products = new ArrayList<Product>();
		}
		products.add(product);
		productMap.put(product.getName(), products);
	}
	
	public int getCountOfInventoryProducts() {		
		return null == productMap ? 0 : productMap.size();
	}
	
	public Product getProduct(final String itemName) throws ProductOutOfStockException{
		final List<Product> products = productMap.get( itemName );
		
		if(   0 != getCountOfInventoryProducts() 
		   || null != products ){
			decreamentStock( itemName);
			return products.get(products.size()-1);
		}
		
		throw new ProductOutOfStockException();
	}
	
	private void decreamentStock(final String itemName) throws ProductOutOfStockException{
		List<Product> productList = productMap.get(itemName);
		if( null == productList)
			throw new ProductOutOfStockException("Product is Out of Stock");
		
		productList.remove(productList.size()-1);
	}
	
	public Integer getStockCount(final String itemName) throws ProductOutOfStockException{
		final List<Product> products = productMap.get( itemName);
		
		if(   0 != getCountOfInventoryProducts() || null != products  ) {
			return products.size();
		}
		throw new ProductOutOfStockException();
	}

}
