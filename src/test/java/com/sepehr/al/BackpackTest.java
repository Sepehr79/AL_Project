package com.sepehr.al;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

import com.sepehr.al.model.Backpack;
import com.sepehr.al.model.Product;


public class BackpackTest {

	@Test
	public void testBackpack() {
		
		// Products
		Product product1 = new Product(5, 30); // Weight, profit
		Product product2 = new Product(10, 20); 
		Product product3 = new Product(2, 10); 
		Product product4 = new Product(10, 25); 
		
		// Backpack
		Backpack backpack = new Backpack(12); 
		
		// Assert
		Assert.assertTrue(backpack.addProduct(product1));
		Assert.assertTrue(backpack.addProduct(product3));
		Assert.assertFalse(backpack.addProduct(product2)); // Can't add product2 to the backpack
		Assert.assertFalse(backpack.addProduct(product4)); // Can't add product4 to the backpack
		
		Assert.assertEquals(backpack.getProfit(), 40);
		Assert.assertEquals(backpack.getWeight(), 7);
	}
	
	@Test
	public void testRecursiveAddWorks() {
		
		Product product1 = new Product(3, 50); // Weight, profit
		Product product2 = new Product(4, 40); 
		Product product3 = new Product(8, 60); 
		
		Backpack backpack = new Backpack(10);
		
		List<Product> products = new LinkedList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		backpack.addProducts(products);
		
		Assert.assertEquals(backpack.getWeight(), 7);
		Assert.assertEquals(backpack.getCapacity(), 3);
		Assert.assertEquals(backpack.getProfit(), 90);
		
	}
	
	@Test
	public void testHarderForBackpack() {
		Product product1 = new Product(10, 15); // Weight, profit
		Product product2 = new Product(5, 15); 
		Product product3 = new Product(20, 19); 
		Product product4 = new Product(10, 15); 
		Product product5 = new Product(30, 20);
		
		List<Product> products = new LinkedList<Product>();
		products.addAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		Backpack backpack = new Backpack(50);
		
		// Test get best profit using dynamic programming
		Assert.assertEquals(backpack.getBestProfit(products), 64);
		
		backpack.addProducts(products);
		
		// Test adding best products using recursion by Brute-Force algorithm
		Assert.assertEquals(backpack.getWeight(), 45);
		Assert.assertEquals(backpack.getProfit(), 64);
	}
	
}
