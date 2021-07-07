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
	
	@Test
	public void testAddingProductToBackpack() {
		
		Product product1 = new Product(5, 10); // Weight, profit
		Product product2 = new Product(6, 20); 
		Product product3 = new Product(7, 30); 
		Product product4 = new Product(8, 40); 
		
		Backpack backpack = new Backpack(19);
		
		Assert.assertTrue(backpack.addProduct(product1));
		Assert.assertEquals(backpack.getWeight(), 5);
		Assert.assertEquals(backpack.getProfit(), 10);
		
		Assert.assertTrue(backpack.addProduct(product2));
		Assert.assertEquals(backpack.getWeight(), 11);
		Assert.assertEquals(backpack.getProfit(), 30);
		
		Assert.assertTrue(backpack.addProduct(product3));
		Assert.assertEquals(backpack.getWeight(), 18);
		Assert.assertEquals(backpack.getProfit(), 60);
		
		Assert.assertFalse(backpack.addProduct(product4));
		
	}
	
}
