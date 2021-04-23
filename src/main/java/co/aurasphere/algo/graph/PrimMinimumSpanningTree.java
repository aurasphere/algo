package co.aurasphere.algo.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrimMinimumSpanningTree {

	public static void main(String[] args) {

		int[][] adjacencyMatrix = { 
				{ 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
				{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
				{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
				{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } 
				};

		primMst(adjacencyMatrix);
	}

	private static void primMst(int[][] adjacencyMatrix) {
		Set<Integer> treeSet = new HashSet<Integer>();
		int[] costs = new int[adjacencyMatrix.length];
		int[] parents = new int[adjacencyMatrix.length];

		// O(v - 1)
		for (int i = 1; i < costs.length; i++) {
			costs[i] = Integer.MAX_VALUE;
		}

		// O(v^2)
		while (treeSet.size() < adjacencyMatrix.length) {

			int currentNode = 0;

			// O(v)
			int minCost = Integer.MAX_VALUE;
			for (int vertex = 0; vertex < costs.length; vertex++) {
				if (!treeSet.contains(vertex) && costs[vertex] < minCost) {
					minCost = costs[vertex];
					currentNode = vertex;
				}
			}

			treeSet.add(currentNode);

			// O(v)
			for (int nextNode = 0; nextNode < adjacencyMatrix.length; nextNode++) {
				int cost = adjacencyMatrix[currentNode][nextNode];
				if (cost == 0) {
					continue;
				}
				if (!treeSet.contains(nextNode) && cost < costs[nextNode]) {
					costs[nextNode] = cost;
					parents[nextNode] = currentNode;
				}
			}
		}

		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(costs));
	}

}
