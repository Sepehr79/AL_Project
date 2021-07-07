package com.sepehr.al;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

import com.sepehr.al.model.Algorithms;
import com.sepehr.al.model.Backpack;
import com.sepehr.al.model.Product;


public class AlgorithmsTest {
	
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
	
	
}
