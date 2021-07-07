package com.sepehr.al;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

import com.sepehr.al.model.Algorithms;
import com.sepehr.al.model.Backpack;
import com.sepehr.al.model.Product;


public class AlgorithmsTest {

	@Test
	public void testBackpack() {
		
//		// Products
//		Product product1 = new Product(5, 30); // Weight, profit
//		Product product2 = new Product(10, 20); 
//		Product product3 = new Product(2, 10); 
//		Product product4 = new Product(10, 25); 
//		
//		// Backpack
//		Backpack backpack = new Backpack(12); 
//		
//		// Assert
//		Assert.assertTrue(backpack.addProduct(product1));
//		Assert.assertTrue(backpack.addProduct(product3));
//		Assert.assertFalse(backpack.addProduct(product2)); // Can't add product2 to the backpack
//		Assert.assertFalse(backpack.addProduct(product4)); // Can't add product4 to the backpack
//		
//		Assert.assertEquals(backpack.getProfit(), 40);
//		Assert.assertEquals(backpack.getWeight(), 7);
//		
//		
//		// Test get sorted products
//		List<Product> sortedProduct = Algorithms.getSortedProducts(backpack.getProducts());
//		
//		Product firstProduct = sortedProduct.get(0);
//		
//		Assert.assertEquals(firstProduct.getProfit(), 10);
//		
//		Product lastProduct = sortedProduct.get(sortedProduct.size() - 1);
//		
//		Assert.assertEquals(lastProduct.getWeight(), 5);
	}
	
	@Test
	public void testRecursiveAddBestProducts() {
		
		Product product1 = new Product(3, 50); // Weight, profit
		Product product2 = new Product(4, 40); 
		Product product3 = new Product(8, 60); 
		
		List<Product> products = Arrays.asList(product1, product2, product3);
		
		// Get best products
		List<Product> bestProducts = Algorithms.recursiveAddBestProducts(10, products);
		
		Assert.assertEquals(bestProducts.size(), 2);
		
		int sum = 0;
		for(Product product: bestProducts)
			sum += product.getProfit();
		
		Assert.assertEquals(sum, 90);
	}
	
	@Test
	public void testDynamicGetBestProfit() {
		Product product1 = new Product(10, 15); // Weight, profit
		Product product2 = new Product(5, 15); 
		Product product3 = new Product(20, 19); 
		Product product4 = new Product(10, 15); 
		Product product5 = new Product(30, 20);
		
		List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);
		
		int bestProfit = Algorithms.getBestProfit(50, products);
		
		Assert.assertEquals(bestProfit, 64);
	}
	
	@Test
	public void testGreedyGetBestProfit() {
		Product product1 = new Product(5, 20); // 4
		Product product2 = new Product(10, 30); // 3
		Product product3 = new Product(10, 20); // 2
		Product product4 = new Product(5, 30); // 6
		
		List<Product> products = Arrays.asList(product1, product2, product3, product4);
		
		int bestGreedyProfit = Algorithms.getBestGreedyProfit(15, products);
		
		Assert.assertEquals(bestGreedyProfit, 65);
	}
	
//	@Test
//	public void testHarderForBackpack() {
//		Product product1 = new Product(10, 15); // Weight, profit
//		Product product2 = new Product(5, 15); 
//		Product product3 = new Product(20, 19); 
//		Product product4 = new Product(10, 15); 
//		Product product5 = new Product(30, 20);
//		
//		List<Product> products = new LinkedList<Product>();
//		products.addAll(Arrays.asList(product1, product2, product3, product4, product5));
//		
//		Backpack backpack = new Backpack(50);
//		
//		// Test get best profit using dynamic programming
//		Assert.assertEquals(Backpack.getBestProfit(50, products), 64);
//		
//		backpack.addProducts(products);
//		
//		// Test adding best products using recursion by Brute-Force algorithm
//		Assert.assertEquals(backpack.getWeight(), 45);
//		Assert.assertEquals(backpack.getProfit(), 64);
//	}
//	
//	@Test
//	public void testGetBestGreedyProfit() {
//		Product product1 = new Product(5, 20); // 4
//		Product product2 = new Product(10, 30); // 3
//		Product product3 = new Product(10, 20); // 2
//		Product product4 = new Product(5, 30); // 6
//		
//		List<Product> products = Arrays.asList(product1, product2, product3, product4);
//		
//		
//		Backpack backpack = new Backpack(15);
//		
//		int bestGreedyProfit = backpack.getBestGreedyProfit(products);
//		
//		Assert.assertEquals(bestGreedyProfit, 65);
//	}
	
}
