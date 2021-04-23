package co.aurasphere.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
	
	
	// A -> B -> C
	// |   /    ^
	// V  V    /
	//   D -> E
	public static void main(String[] args) {
		// Adjacency list
		List<Integer>[] adjacencyList = new List[5];
		adjacencyList[0] = Arrays.asList(1, 3); // A
		adjacencyList[1] = Arrays.asList(2, 3); // B
		adjacencyList[2] = Arrays.asList(); // C
		adjacencyList[3] = Arrays.asList(4); // D
		adjacencyList[4] = Arrays.asList(2); // E
		
		List<Integer> sorted = topologicalSort(adjacencyList);
		
		System.out.println(sorted);
	}
	
	
	public static List<Integer> topologicalSort(List<Integer>[] adjacencyList) {
		List<Integer> sorted = new ArrayList<Integer>();
		int[] indegreeArray = new int[adjacencyList.length];
		Queue<Integer> nodesQueue = new LinkedList<Integer>();
		
		// Precomputes the indegree
		// O(v + e)
		for (List<Integer> edges : adjacencyList) {
			for (Integer vertex : edges) {
				indegreeArray[vertex]++;
			}
		}
		
		// Adds the nodes with indegree 0 to the queue
		// O(v)
		for (int i = 0; i < indegreeArray.length; i++) {
			if (indegreeArray[i] == 0) {
				nodesQueue.offer(i);
			}
		}
		
		// O(v)
		while(!nodesQueue.isEmpty()) {
			int currentElement = nodesQueue.poll();
			sorted.add(currentElement);
			
			// Removes the edge
			for (Integer neightbour: adjacencyList[currentElement]) {
				indegreeArray[neightbour]--;
				if (indegreeArray[neightbour] == 0) {
					nodesQueue.offer(neightbour);
				}
			}
		}
		
		return sorted;
	}

}