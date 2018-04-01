package co.aurasphere.algo.pathfind;

import org.junit.Assert;

/**
 * Base test class for a pathfinding algorithm.
 * 
 * @author Donato Rimenti
 *
 */
public class BasePathfindingTest {

	/**
	 * Grid which represents a path that can be solved. A 1 represents an
	 * obstacle.
	 */
	private int[][] obstaclesSetOne = { { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 }, { 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

	/**
	 * Grid which represents a path that can't be solved. A 1 represents an
	 * obstacle.
	 */
	private int[][] obstaclesImpossible = { { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 }, { 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

	/**
	 * Tests a path finding algorithm with a dataset that allows a solution.
	 * 
	 * @param solver
	 *            the pathfinding algorithm to test
	 * @return the solution found, if any
	 */
	protected Node mainTestPossible(PathFinder solver) {
		return mainTest(solver, obstaclesSetOne);
	}

	/**
	 * Tests a path finding algorithm with a dataset that doesn't allows a
	 * solution.
	 * 
	 * @param solver
	 *            the pathfinding algorithm to test
	 * @return the solution found, if any
	 */
	protected Node mainTestImpossible(PathFinder solver) {
		return mainTest(solver, obstaclesImpossible);
	}

	/**
	 * Executes the main testing logic by solving a grid using a specific
	 * pathfinding algorithm and returning the solution.
	 * 
	 * @param solver
	 *            the pathfinding algorithm to use
	 * @param grid
	 *            the grid to solve
	 * @return the solution found, if any
	 */
	protected Node mainTest(PathFinder solver, int[][] grid) {
		// Inits the obstacle set.
		Simple2DNode.setObstacleSet(grid);

		// Starting and ending point.
		Simple2DNode start = new Simple2DNode(1, 1);
		Simple2DNode goal = new Simple2DNode(5, 4);

		// Returns the solution.
		return solver.findPath(start, goal);
	}

	/**
	 * Checks that the solution found has a set amount of nodes and prints it.
	 * 
	 * @param currentNode
	 *            the current node to print
	 * @param nodeCounter
	 *            the counter to the current node
	 * @param expectedNodes
	 *            the number of nodes expected
	 */
	protected void printSolution(Node currentNode, int nodeCounter, int expectedNodes) {
		if (currentNode.getParent() != null) {
			printSolution(currentNode.getParent(), ++nodeCounter, expectedNodes);
		} else {
			System.out.println("Number of nodes in the solution: " + nodeCounter);
			Assert.assertEquals(expectedNodes, nodeCounter);
		}
		System.out.println(currentNode);
	}

}