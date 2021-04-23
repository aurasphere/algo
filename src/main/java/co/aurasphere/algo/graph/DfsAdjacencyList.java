package co.aurasphere.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DfsAdjacencyList {
	
//   DFS with adjacency list (recursive)
//	 DFS with adjacency list (iterative with stack)	
//	 DFS with adjacency matrix (recursive)
//	 DFS with adjacency matrix (iterative with stack)
	
//	 BFS with adjacency list
//	 BFS with adjacency matrix
	
//	 single-source shortest path (Dijkstra)
//	 minimum spanning tree
//	DFS-based algorithms (see Aduni videos above):
//	 check for cycle (needed for topological sort, since we'll check for cycle before starting)
//	 topological sort
	
//	 count connected components in a graph
//	 list strongly connected components
//	 check for bipartite graph
	
	

	// 0 -> 1 -> 2
	// |   /    ^
	// V  V    /
	//   3 -> 4
	public static void main(String[] args) {
		List<Integer>[] adjacencyList = new List[5];
		adjacencyList[0] = Arrays.asList(1, 3);
		adjacencyList[1] = Arrays.asList(2, 3);
		adjacencyList[2] = Arrays.asList();
		adjacencyList[3] = Arrays.asList(4);
		adjacencyList[4] = Arrays.asList(2);
		
		int[][] adjacencyMatrix = {
				{0, 1, 0, 1, 0},
				{0, 0, 1, 1, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 1},
				{0, 0, 1, 0, 0}
			};
		
		dfsAdjacencyListRecursive(adjacencyList, 0, new ArrayList<Integer>());
		dfsAdjacencyListIterative(adjacencyList, 0);
		dfsAdjacencyMatrixRecursive(adjacencyMatrix, 0, new ArrayList<Integer>());
		dfsAdjacencyMatrixIterative(adjacencyMatrix, 0);
		
		bfsAdjacencyList(adjacencyList, 0);
		bfsAdjacencyMatrix(adjacencyMatrix, 0);
	}
	
	private static void bfsAdjacencyMatrix(int[][] adjacencyMatrix, int firstVertex) {
		Queue<Integer> toVisit = new LinkedList<Integer>();
		toVisit.offer(firstVertex);
		List<Integer> orderedNodes = new ArrayList<>();
		boolean[] visited = new boolean[adjacencyMatrix.length];
		
		while (!toVisit.isEmpty()) {
			Integer nextElement = toVisit.poll();
			orderedNodes.add(nextElement);
			int[] neightbours = adjacencyMatrix[nextElement];
			for (int i = 0; i < neightbours.length; i++) {
				if (neightbours[i] == 0) {
					continue;
				}
				if (!visited[i]) {
					toVisit.offer(i);
					visited[i] = true;
				}
			}
		}
		
		System.out.println(orderedNodes);
	}

	private static void bfsAdjacencyList(List<Integer>[] adjacencyList, int firstVertex) {
		Queue<Integer> toVisit = new LinkedList<Integer>();
		toVisit.offer(firstVertex);
		List<Integer> elementsSorted = new ArrayList<>();
		boolean[] visited = new boolean[adjacencyList.length];
		
		while (!toVisit.isEmpty()) {
			Integer nextElement = toVisit.poll();
			elementsSorted.add(nextElement);
			List<Integer> neightbours = adjacencyList[nextElement];
			for (Integer neightbour : neightbours) {
				if (!visited[neightbour]) {
					visited[neightbour] = true;
					toVisit.offer(neightbour);
				}
			}
		}
		
		System.out.println(elementsSorted);
	}

	public static void dfsAdjacencyListRecursive(List<Integer>[] adjacencyList, Integer nextVertex, List<Integer> initial) {
		initial.add(nextVertex);
		List<Integer> neightbours = adjacencyList[nextVertex];
		if (neightbours.isEmpty()) {
			System.out.println(initial);
		}
		
		for (Integer vertex : neightbours) {
			List<Integer> result = new ArrayList<Integer>(initial);
			dfsAdjacencyListRecursive(adjacencyList, vertex, result);
		}
		
	}
	
	public static void dfsAdjacencyListIterative(List<Integer>[] adjacencyList, Integer nextVertex) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(nextVertex);
		int dfsElementsCounter = 0;
		LinkedList<Integer> elements = new LinkedList<>();
		
		while (!stack.isEmpty()) {
			Integer nextElement = stack.pop();
			elements.add(nextElement);
			
			List<Integer> neightbours = adjacencyList[nextElement];
			for (Integer vertex : neightbours) {
				stack.push(vertex);
			}
			
			if (neightbours.isEmpty()) {
				System.out.println(elements);
				
				while (dfsElementsCounter > 0) {
					dfsElementsCounter--;
					elements.removeLast();
				}

			} else {
				dfsElementsCounter++;
			}
	
		}
	}
	
	private static void dfsAdjacencyMatrixRecursive(int[][] adjacencyMatrix, int nextVertex, List<Integer> initial) {
		boolean neightbourAvailable = false;
		initial.add(nextVertex);
		
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[nextVertex][i] == 0) {
				continue;
			}
			neightbourAvailable = true;
			List<Integer> elements = new ArrayList<>(initial);
			dfsAdjacencyMatrixRecursive(adjacencyMatrix, i, elements);
		}
		
		if (!neightbourAvailable) {
			System.out.println(initial);
		}
	}
	
	private static void dfsAdjacencyMatrixIterative(int[][] adjacencyMatrix, int initialVertex) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(initialVertex);
		int removeCounter = 0;
		LinkedList<Integer> elements = new LinkedList<>();
		
		while (!stack.isEmpty()) {
			boolean neightbourFound = false;
			Integer nextVertex = stack.pop();
			int[] neightbours = adjacencyMatrix[nextVertex];
			elements.add(nextVertex);
			
			for (int i = 0; i < neightbours.length; i++) {
				if (neightbours[i] == 0) {
					continue;
				}
				neightbourFound = true;
				stack.push(i);
			}
			
			if (!neightbourFound) {
				System.out.println(elements);
				while (removeCounter > 0) {
					removeCounter--;
					elements.removeLast();
				}
			} else {
				removeCounter++;
			}
		
		}
	}
	

}