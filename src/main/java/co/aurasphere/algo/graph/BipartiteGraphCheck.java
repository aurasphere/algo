package co.aurasphere.algo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BipartiteGraphCheck {

	
	public static void main(String[] args) {
		// 0 -> 1 -> 2
		// | / 		^
		// V V 		/
		// 3 -> 4
		List<Integer>[] notBipartiteGraph = new List[5];
		notBipartiteGraph[0] = Arrays.asList(1, 3);
		notBipartiteGraph[1] = Arrays.asList(2, 3);
		notBipartiteGraph[2] = Arrays.asList();
		notBipartiteGraph[3] = Arrays.asList(4);
		notBipartiteGraph[4] = Arrays.asList(2);
		
		// 0 -> 1 -> 2
		// | 	|		
		// V    V 		
		// 3 -> 4
		List<Integer>[] bipartiteGraph = new List[5];
		bipartiteGraph[0] = Arrays.asList(1, 3);
		bipartiteGraph[1] = Arrays.asList(2, 4);
		bipartiteGraph[2] = Arrays.asList();
		bipartiteGraph[3] = Arrays.asList(4);
		bipartiteGraph[4] = Arrays.asList();

		System.out.println(isGraphBipartite(bipartiteGraph, 0));
		System.out.println(isGraphBipartite(notBipartiteGraph, 0));
	}

	private static boolean isGraphBipartite(List<Integer>[] adjacencyList, int startingNode) {
		int[] sets = new int[adjacencyList.length];
		Queue<Integer> toVisit = new LinkedList<Integer>();
		toVisit.offer(startingNode);
		int currentColor = 1;

		while (!toVisit.isEmpty()) {
			System.out.println(Arrays.toString(sets));
			Integer nextNode = toVisit.poll();

			sets[nextNode] = currentColor;
			currentColor = currentColor == 1 ? 2 : 1;

			for (Integer neightbour : adjacencyList[nextNode]) {
				// Already visited.
				if (sets[neightbour] == 0) {
					toVisit.offer(neightbour);
				} else if (sets[neightbour] != currentColor) {
					// Wrong color.
					return false;
				}
			}
		}
		return true;
	}

}
