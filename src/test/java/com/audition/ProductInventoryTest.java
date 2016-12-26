package test.java.com.audition;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.com.inventory.ProductInventory;
import main.java.com.model.Product;
import main.java.com.vending.exception.ProductOutOfStockException;

/**
 * 
 * @author Yergalem
 *
 */
public class ProductInventoryTest {
	
	private ProductInventory  productInventory;
	
	@Before
	public void setUp(){
		productInventory = new ProductInventory();
	}
	
	@Test
	public void shouldLoadProduct() throws ProductOutOfStockException{
		final List<Product> products = new ArrayList<Product>();
		final Product product = new Product("cola");
		
		for(int i=1; i<=5; i++){
			products.add(product);
		}
		productInventory.load(products);
		assertEquals(5, productInventory.getStockCount("cola").intValue());
	}
	
	@Test
	public void shouldLoadProducts() throws ProductOutOfStockException {
		
		final List<Product> colas = new ArrayList<Product>();
		final Product cola = new Product("cola");
		
		for(int i=1; i<=5; i++){
			colas.add(cola);
		}
		productInventory.load(colas);
		
		final List<Product> chips = new ArrayList<Product>();
		final Product chip = new Product("chips");

		for(int i=1; i<=3; i++){
			chips.add(chip);
		}
		productInventory.load(chips);
		
		final List<Product> candies = new ArrayList<Product>();
		final Product candy = new Product("candy");
		
		for(int i=1; i<=4; i++){
			candies.add(candy);
		}
		productInventory.load(candies);
		
		assertEquals(5, productInventory.getStockCount("cola").intValue());
		assertEquals(3, productInventory.getStockCount("chips").intValue());
		assertEquals(4, productInventory.getStockCount("candy").intValue());
		//assertEquals(0, productInventory.getStockCount("onion").intValue());
	}
	
	@Test
	public void shouldReduceTheStock() throws ProductOutOfStockException{
		final List<Product> products = new ArrayList<Product>();
		final Product product = new Product("cola");
		
		for(int i=1; i<=5; i++){
			products.add(product);
		}
		productInventory.load(products);
		assertEquals(5, productInventory.getStockCount("cola").intValue());
		assertEquals(product, productInventory.getProduct("cola"));
		assertEquals(4, productInventory.getStockCount("cola").intValue());
		assertEquals(product, productInventory.getProduct("cola"));
		assertEquals(3, productInventory.getStockCount("cola").intValue());
	}
	
	@Test(expected = ProductOutOfStockException.class)
	public void shouldShowProductOutOfStockMessage() throws ProductOutOfStockException{
		productInventory.getProduct("cola");		
	}

}
