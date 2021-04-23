package co.aurasphere.algo.graph;

import java.util.Arrays;
import java.util.List;

public class DfsDetectCycle {

	public static void main(String[] args) {

		// 0 -> 1 -> 2
		// | / ^
		// V V /
		// 3 -> 4
		List<Integer>[] adjacencyListNoCycle = new List[5];
		adjacencyListNoCycle[0] = Arrays.asList(1, 3);
		adjacencyListNoCycle[1] = Arrays.asList(2, 3);
		adjacencyListNoCycle[2] = Arrays.asList();
		adjacencyListNoCycle[3] = Arrays.asList(4);
		adjacencyListNoCycle[4] = Arrays.asList(2);

		// 0 -> 1 -> 2
		// | / ^
		// V V /
		// 3 -> 4
		List<Integer>[] adjacencyListCycle = new List[5];
		adjacencyListCycle[0] = Arrays.asList(1, 3);
		adjacencyListCycle[1] = Arrays.asList(2, 3);
		adjacencyListCycle[2] = Arrays.asList();
		adjacencyListCycle[3] = Arrays.asList(4);
		adjacencyListCycle[4] = Arrays.asList(2);

		for (int i = 0; i < adjacencyListNoCycle.length; i++) {
			if (detectCycleDfs(adjacencyListNoCycle, i, new boolean[adjacencyListNoCycle.length],
					new boolean[adjacencyListNoCycle.length])) {
				System.out.println("Cycle found in list with no cycle!");
			}
		}

		for (int i = 0; i < adjacencyListCycle.length; i++) {
			if (detectCycleDfs(adjacencyListCycle, i, new boolean[adjacencyListCycle.length],
					new boolean[adjacencyListCycle.length])) {
				System.out.println("Cycle found in list with cycle!");
			}
		}
	}

	private static boolean detectCycleDfs(List<Integer>[] adjacencyList, int currentVertex, boolean[] visited,
			boolean[] currentParents) {
		List<Integer> neightbours = adjacencyList[currentVertex];

		// Self loop.
		if (currentParents[currentVertex]) {
			return true;
		}

		// We already visited this note, so we skip it.
		if (visited[currentVertex]) {
			return false;
		}

		currentParents[currentVertex] = true;
		visited[currentVertex] = true;

		for (int nextVertex : neightbours) {
			if (detectCycleDfs(adjacencyList, nextVertex, visited, currentParents)) {
				return true;
			}
		}

		// Backtracking.
		currentParents[currentVertex] = false;

		return false;
	}

}
