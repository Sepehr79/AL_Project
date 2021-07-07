package com.sepehr.al.model;

import java.util.LinkedList;
import java.util.List;

public class Backpack {
	
	// Fields
	private int capacity;
	private final List<Product> products = new LinkedList<Product>();
	
	// Constructor
	public Backpack(int capacity) {
		this.capacity = capacity;
	}
	
	
	/**
	 * Add best products to the backpack
	 * @param products
	 * @return list of best products
	 */
	public List<Product> addProducts(List<Product> products) {
		this.products.addAll(Algorithms.recursiveAddBestProducts(capacity - getWeight(), products));  
		return this.products;
	}
	
	public boolean addProduct(Product product) {
		if (getWeight() + product.getWeight() <= capacity) {
			products.add(product);
			return true;
		}
		return false;
	}
	
	public int getWeight() {
		return products.stream().map(Product::getWeight).mapToInt(Integer::intValue).sum();
	}
	
	public int getProfit() {
		return products.stream().map(Product::getProfit).mapToInt(Integer::intValue).sum();
	}
	
	public int getCapacity() {
		return capacity;
	}

	public List<Product> getProducts() {
		return products;
	}
	
}
