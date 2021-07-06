package com.sepehr.al.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Backpack {
	
	// Debugging
	private static final Logger LOGGER = Logger.getLogger(Backpack.class.getName());
	private int recursionCounter = 1;
	
	// Fields
	private int capacity;
	private final List<Product> products = new LinkedList<Product>();
	
	public Backpack(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public int getWeight() {
		return products.stream().map(Product::getWeight).mapToInt(Integer::intValue).sum();
	}
	
	public int getProfit() {
		return products.stream().map(Product::getProfit).mapToInt(Integer::intValue).sum();
	}
	
	public boolean addProduct(Product work) {
		if (getWeight() + work.getWeight() <= capacity) {
			products.add(work);
			return true;
		}
		return false;
	}
	
	/**
	 * @return list of sorted products by (profit / weight)
	 */
	public List<Product> getSortedProducts(List<Product> products) {
		return products.stream().sorted((product1, product2) -> {
			Integer prof1 = product1.getProfit() / product1.getWeight();
			Integer prof2 = product2.getProfit() / product2.getWeight();
			
			return prof1.compareTo(prof2);
		}).collect(Collectors.toList());	
	}
	
	/**
	 * Add best products to the backpack
	 * @param products
	 * @return list of best products
	 */
	public List<Product> addProducts(List<Product> products) {
		this.products.addAll(recursiveAddProducts(capacity, products));  
		this.capacity -= getWeight();
		return this.products;
	}
	
	/**
	 * @param products
	 * @return best profit of products
	 */
	public int getBestProfit(List<Product> products) {
		return getBestProfit(capacity, products);
	}
	
	/**
	 * Solve 0-1 Knapsack problem using dynamic programming
	 * @return best profit of products
	 */
	private int getBestProfit(int weight, List<Product> products) {
		int T[][] = new int[products.size() + 1][weight + 1];
		LOGGER.info("Starting editing matrix: \n" + getMatrixView(T));
		
		
		for (int i = 0; i <= products.size(); i++) {
			for (int w = 0; w <= weight; w++) {
				if (i == 0 || w == 0) {
					T[i][w] = 0;
				}else if (products.get(i - 1).getWeight() <= w) {
					T[i][w] = Math.max
							(
					products.get(i - 1).getProfit() + T[i - 1][w - products.get(i - 1).getWeight()]
							,
					T[i - 1][w]
							);
				}else {
					T[i][w] = T[i - 1][w];
				}
				LOGGER.info(getMatrixView(T));
			}
		}
		
		return T[products.size()][weight];
	}
	
	public int getBestGreedyProfit(List<Product> products) {
		List<Product> sortedProducts = getSortedProducts(products);
		int bestProfit = 0;
		int maximumWeight = 0;
		
		for(int i = products.size() - 1 ; i >= 0 ; i--) {
			if (maximumWeight + sortedProducts.get(i).getWeight() < capacity) {
				maximumWeight += sortedProducts.get(i).getWeight();
				bestProfit += sortedProducts.get(i).getProfit();
			}else if(maximumWeight < capacity) {
				int neededWeight = capacity - maximumWeight;
				
				LOGGER.info("nedded weight: " + neededWeight );
				
				bestProfit += (int) products.get(i).getProfit() * ( (double) neededWeight / products.get(i).getWeight());
				break;
			}
			
			LOGGER.info("best profit: " +bestProfit);
		}
		
		return bestProfit;
	}
	
	
	/**
	 * Solve 0-1 Knapsack problem using recursion 
	 * @return best list of products
	 */
	private List<Product> recursiveAddProducts(int weight ,List<Product> products) {
		
		LOGGER.info("Execute recusive method for: " + recursionCounter++);
		
		if (products.size() == 1 && products.get(0).getWeight() <= weight) {
			List<Product> newProducts = new LinkedList<Product>();
			newProducts.add(products.get(0));
			return newProducts;
		}else if (products.size() == 1 && products.get(0).getWeight() > weight ) {
			return new LinkedList<Product>();
		}
		
		List<Product> currentProducts = recursiveAddProducts(weight - products.get(0).getWeight(), products.subList(1, products.size()));
		currentProducts.add(products.get(0));
		
		List<Product> withoutCurrentProduct = recursiveAddProducts(weight, products.subList(1, products.size()));
		
		if (getProfit(currentProducts) >= getProfit(withoutCurrentProduct) && getWeight(currentProducts) <= weight) {
			return currentProducts;
		}else if (getProfit(withoutCurrentProduct) >= getProfit(currentProducts) && getWeight(withoutCurrentProduct) <= weight ) {
			return withoutCurrentProduct;
		}
		
		return new LinkedList<Product>();
	}
	
	
	private int getWeight(List<Product> products) {
		int weight = 0;
		for (Product product: products)
			weight += product.getWeight();
		return weight;
	}
	
	private int getProfit(List<Product> products) {
		int profit = 0;
		for (Product product: products)
			profit += product.getProfit();
		return profit;
	}
	
	private String getMatrixView(int[][] matrix) {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		
		for(int i = 0 ; i < matrix.length ; i++) {
			for(int j = 0; j < matrix[i].length ; j++) {
				if (matrix[i][j] >= 100 ) {
					builder.append(matrix[i][j]);
				}else if (matrix[i][j] >= 10) {
					builder.append(matrix[i][j] + " ");
				}else if (matrix[i][j] < 10) {
					builder.append(matrix[i][j] + "  ");
				}
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	
}
