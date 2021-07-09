package com.sepehr.al;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

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
		
		Assertions.assertEquals(backpack.getProfit(), 64);
		Assertions.assertEquals(backpack.getProducts().size(), 4);
	}
	
	@Test
	public void testAddingProductToBackpack() {
		
		Product product1 = new Product(5, 10); // Weight, profit
		Product product2 = new Product(6, 20); 
		Product product3 = new Product(7, 30); 
		Product product4 = new Product(8, 40); 
		
		Backpack backpack = new Backpack(19);
		
		Assertions.assertTrue(backpack.addProduct(product1));
		Assertions.assertEquals(backpack.getWeight(), 5);
		Assertions.assertEquals(backpack.getProfit(), 10);
		
		Assertions.assertTrue(backpack.addProduct(product2));
		Assertions.assertEquals(backpack.getWeight(), 11);
		Assertions.assertEquals(backpack.getProfit(), 30);
		
		Assertions.assertTrue(backpack.addProduct(product3));
		Assertions.assertEquals(backpack.getWeight(), 18);
		Assertions.assertEquals(backpack.getProfit(), 60);
		
		Assertions.assertFalse(backpack.addProduct(product4));
		
	}
	
}
