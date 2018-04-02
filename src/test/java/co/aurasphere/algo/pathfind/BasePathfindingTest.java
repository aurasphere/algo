/*
 * MIT License
 *
 * Copyright (c) 2018 Donato Rimenti
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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