package co.aurasphere.algo.graph;

import java.util.Arrays;

public class BellmanFord {

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
			{0, 5, 3, 2, 4, 0},
			{0, 0, 9, 4, 5, 7},
			{1, -2, 0, -1, 1, 9},
			{-2, 1, 5, 0, 1, 0},
			{-4, 4, 3, -1, 0, 1},
			{1, 2, 3, 1, -1, 0}
		};
		
		int[] parents = new int[matrix.length];
		int[] costs = new int[matrix.length];
		
		for (int i = 0; i < costs.length; i++) {
			costs[i] = Integer.MAX_VALUE;
		}
		costs[0] = 0;
		
		// O (V*E)
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix.length * matrix.length; j++) {
				int startingNode = j / matrix.length;
				int destinationNode = j % matrix.length;
				
				if (matrix[startingNode][destinationNode] == 0) {
					// No path.
					continue;
				}
				
				int tentativeDistance = matrix[startingNode][destinationNode] + costs[startingNode];
				if (tentativeDistance < costs[destinationNode]) {
					costs[destinationNode] = tentativeDistance;
					parents[destinationNode] = startingNode;
				}
			}
		}
		
		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(costs));
		
		// O (E)
		for (int j = 0; j < matrix.length * matrix.length; j++) {
			int startingNode = j / matrix.length;
			int destinationNode = j % matrix.length;
			if (matrix[startingNode][destinationNode] == 0) {
				// No path.
				continue;
			}
			int tentativeDistance = matrix[startingNode][destinationNode] + costs[startingNode];
			if (tentativeDistance < costs[destinationNode]) {
				// Negative cycle found
				throw new IllegalArgumentException("Negative path: " + startingNode + " -> " + destinationNode);
			}
		}
		
		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(costs));
	}

}