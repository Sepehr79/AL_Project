package com.sepehr.al.model;

public class Product {

	private int weight;
	private int profit;
	
	public Product(int weight, int profit) {
		this.weight = weight;
		this.profit = profit;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Product [weight=" + weight + ", profit=" + profit + "]";
	}
	
	
}
