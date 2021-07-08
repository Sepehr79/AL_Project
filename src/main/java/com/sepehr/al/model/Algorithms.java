package com.sepehr.al.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Algorithms {

	// Debugging
	private static final Logger LOGGER = Logger.getLogger(Algorithms.class.getName());
	private static int recursionCounter = 0;
	
	// Singleton
	private static final Algorithms algorithms = new Algorithms();
	
	private Algorithms() {
		
	}
	
	public static Algorithms getAllgorithms() {
		return algorithms;
	}
	
	/**
	 * Solve 0-1 Knapsack problem using recursion 
	 * @return best list of products
	 */
	public List<Product> recursiveAddBestProducts(int weight ,List<Product> products) {
		
		// How many times this function is executed?
		LOGGER.info("Execute recusive method for: " + recursionCounter++);
		
		if (products.size() == 1 && products.get(0).getWeight() <= weight) {
			List<Product> newProducts = new LinkedList<Product>();
			newProducts.add(products.get(0));
			return newProducts;
		}else if (products.size() == 1 && products.get(0).getWeight() > weight ) {
			return new LinkedList<Product>();
		}
		
		List<Product> currentProducts = recursiveAddBestProducts(weight - products.get(0).getWeight(), products.subList(1, products.size()));
		currentProducts.add(products.get(0));
		
		List<Product> withoutCurrentProduct = recursiveAddBestProducts(weight, products.subList(1, products.size()));
		
		if (getProfit(currentProducts) >= getProfit(withoutCurrentProduct) && getWeight(currentProducts) <= weight) {
			return currentProducts;
		}else if (getProfit(withoutCurrentProduct) >= getProfit(currentProducts) && getWeight(withoutCurrentProduct) <= weight ) {
			return withoutCurrentProduct;
		}
		
		return new LinkedList<Product>();
	}
	
	/**
	 * Solve 0-1 Knapsack problem using dynamic programming
	 * @return best profit of products
	 */
	public int getBestProfit(int weight, List<Product> products) {
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
	
	/**
	 * Solving Fractional knapsack problem
	 * @param weight
	 * @param products
	 * @return best profit using greedy algorithm
	 */
	public int getBestGreedyProfit(int weight ,List<Product> products) {
		List<Product> sortedProducts = getSortedProducts(products);
		int bestProfit = 0;
		int maximumWeight = 0;
		
		for(int i = products.size() - 1 ; i >= 0 ; i--) {
			if (maximumWeight + sortedProducts.get(i).getWeight() < weight) {
				maximumWeight += sortedProducts.get(i).getWeight();
				bestProfit += sortedProducts.get(i).getProfit();
			}else if(maximumWeight < weight) {
				int neededWeight = weight - maximumWeight;
				
				LOGGER.info("nedded weight: " + neededWeight );
				
				bestProfit += (int) products.get(i).getProfit() * ( (double) neededWeight / products.get(i).getWeight());
				break;
			}
			
			LOGGER.info("best profit: " +bestProfit);
		}
		
		return bestProfit;
	}
	
	/**
	 * Backtracking
	 * @param weight
	 * @param products
	 * @return best profit of products
	 */
	public int getBacktrackingBestProfit(int weight, List<Product> products) {
		
		if (products.size() == 1) {
			if (products.get(0).getWeight() <= weight) {
				return products.get(0).getProfit();
			}
			return 0;
		}
		
		// List of current depth numbers
		List<Integer> numbersIntegers = new ArrayList<Integer>();
		
		for(int i = 0 ; i < products.size(); i++) {
			
			// Copy from current list
			List<Product> copyProducts = new LinkedList<Product>(products);
			
			// Remove current product from copy list
			copyProducts.remove(products.get(i));
			
			if (products.get(i).getWeight() <= weight) {
				
				numbersIntegers.add(products.get(i).getProfit() + getBacktrackingBestProfit(weight - products.get(i).getWeight(), copyProducts));
				
			}else {
				
				numbersIntegers.add(0);
				
			}
			
		}
		
		return numbersIntegers.stream().max(Integer::compareTo).get();
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
	 * @param matrix
	 * @return string view of matrix
	 */
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
}
