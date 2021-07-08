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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + profit;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (profit != other.profit)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	
	
	
}
