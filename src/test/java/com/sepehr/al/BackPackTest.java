package com.sepehr.al;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sepehr.al.model.Backpack;
import com.sepehr.al.model.Product;

public class BackPackTest {
	
	@Test
	public void testAddingProductToBackPack() {
		
		Product product1 = new Product(10, 15); // Weight, profit
		Product product2 = new Product(5, 15); 
		Product product3 = new Product(20, 19); 
		Product product4 = new Product(10, 15); 
		Product product5 = new Product(30, 20);
		
		List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);
		
		Backpack backpack = new Backpack(50);
		backpack.addProducts(products);
		
		Assert.assertEquals(backpack.getProfit(), 64);
		Assert.assertEquals(backpack.getProducts().size(), 4);
	}
	
}
