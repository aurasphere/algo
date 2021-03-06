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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Dijkistra pathfinding algorithm implementation.
 * 
 * @author Donato Rimenti
 *
 */
public class Dijkistra implements PathFinder {

	/**
	 * Max number of steps allowed by this algorithm. Defaults to
	 * {@link Integer#MAX_VALUE} if not sett manually.
	 */
	private int maxNumberOfSteps = Integer.MAX_VALUE;

	/**
	 * Instantiates a new Dijkistra's algorithm.
	 * 
	 */
	public Dijkistra() {
	}

	/**
	 * Instantiates a new Dijkistra's algorithm.
	 * 
	 * @param maxNumberOfSteps
	 *            the {@link #maxNumberOfSteps}
	 * 
	 */
	public Dijkistra(int maxNumberOfSteps) {
		this.maxNumberOfSteps = maxNumberOfSteps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.pathfind.PathFinder#findPath(co.aurasphere.algo.
	 * pathfind.Node, co.aurasphere.algo.pathfind.Node)
	 */
	public Node findPath(Node start, Node goal) {
		// Unexplored nodes.
		Queue<Node> openList = new LinkedList<Node>();
		// Explored nodes.
		List<Node> closedList = new ArrayList<Node>();
		// Adds the first node to the "to explore" list.
		openList.add(start);

		// Main loop.
		int currentStep = 0;
		while (!openList.isEmpty() && currentStep < maxNumberOfSteps) {
			Node q = openList.poll();

			for (Node successor : q.getAdjacentNodes()) {

				// If we already explored this node we just skip it.
				if (closedList.contains(successor) || openList.contains(successor)) {
					continue;
				}

				// Stop if we reached the end.
				if (successor.equals(goal)) {
					goal.setParent(successor);
					return goal;
				}

				// Adds it to the nodes to explore.
				openList.offer(successor);

			}

			// This node has been fully explored.
			closedList.add(q);
			currentStep++;
		}

		// No path has been found.
		return null;
	}

}