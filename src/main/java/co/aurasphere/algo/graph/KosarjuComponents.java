package co.aurasphere.algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarjuComponents {
	
	static boolean[] visited = new boolean[6];
	
	private static List<List<Integer>> components = new ArrayList<>();
	
	private static Stack<Integer> s = new Stack<Integer>();
	
	public static void main(String[] args) {
		
		// A -> B	  - - D	
		// ^	|	 |	  ^
		// |	V	 V	  |
		// 	- -	C -> E -> F  
		int[][] matrix = new int[][]{
			{0, 1, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 1},
			{0, 0, 0, 1, 0, 0}
		}; 
		
		
		for (int i = 0; i < matrix.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs1(matrix, i);
			}
		}
		
		System.out.println(s);
		
		int[][] transposed = transpose(matrix);
		visited = new boolean[6];
		while(!s.isEmpty()) {
			Integer nextNode = s.pop();
			if (!visited[nextNode]) {
				visited[nextNode] = true;
				List<Integer> component = new ArrayList<>();
				dfs2(transposed, nextNode, component);
				components.add(component);
			}
		}
		System.out.println(components);
	}

	private static int[][] transpose(int[][] matrix) {
		int[][] newMatrix = new int[matrix.length][matrix.length];
 		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				newMatrix[i][j] = matrix[j][i];
			}
		}
 		return newMatrix;
	}
	
	private static void dfs1(int[][] matrix, int current) {
		int[] neightbours = matrix[current];
		for (int i = 0; i < neightbours.length; i++) {
			if (neightbours[i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs1(matrix, i);
			}
		}
		s.push(current);
	}

	private static void dfs2(int[][] matrix, int current, List<Integer> component) {
		component.add(current);
		int[] neightbours = matrix[current];
		for (int i = 0; i < neightbours.length; i++) {
			if (neightbours[i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs2(matrix, i, component);
			}
		}
	}
	

}