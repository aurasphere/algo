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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A* pathfinding algorithm implementation.
 * 
 * @author Donato Rimenti
 *
 */
public class AStar implements Comparator<Node>, PathFinder {

	/**
	 * Starting node of this algorithm.
	 */
	private Node start;

	/**
	 * Final destination to reach with this algorithm.
	 */
	private Node target;

	/**
	 * Epsilon value used for dynamic heuristic function's weight computation.
	 */
	private Double epsilon;

	/**
	 * Estimated max depth value used for dynamic heuristic function's weight
	 * computation.
	 */
	private Integer estimatedMaxDepth;

	/**
	 * Static weight of the heuristic function.
	 */
	private Double staticWeight;

	/**
	 * Metric used as heuristic to determine how close we are to the goal.
	 */
	private Metric hFunction;

	/**
	 * Max number of steps allowed by this algorithm. Defaults to
	 * {@link Integer#MAX_VALUE} if not set manually.
	 */
	private int maxNumberOfSteps = Integer.MAX_VALUE;

	/**
	 * Instantiates a new AStar algorithm with weight 1 for the heuristic
	 * function.
	 * 
	 * @param hFunction
	 *            heuristic function used to compute the distance between the
	 *            current node and the goal
	 */
	public AStar(Metric hFunction) {
		this.hFunction = hFunction;
	}

	/**
	 * Instantiates a new AStar algorithm with weight the value passed as
	 * argument for the heuristic function.
	 *
	 * @param hFunction
	 *            heuristic function used to compute the distance between the
	 *            current node and the goal
	 * @param staticWeight
	 *            the {@link #staticWeight}
	 */
	public AStar(Metric hFunction, double staticWeight) {
		this.hFunction = hFunction;
		this.staticWeight = staticWeight;
	}

	/**
	 * Instantiates a new AStar algorithm with dynamic weight computed with the
	 * values passed as arguments for the heuristic function.
	 *
	 * @param hFunction
	 *            heuristic function used to compute the distance between the
	 *            current node and the goal
	 * @param epsilon
	 *            the {@link #epsilon}
	 * @param estimatedMaxDepth
	 *            the {@link #estimatedMaxDepth}
	 */
	public AStar(Metric hFunction, double epsilon, int estimatedMaxDepth) {
		this.hFunction = hFunction;
		this.epsilon = epsilon;
		this.estimatedMaxDepth = estimatedMaxDepth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.pathfind.PathFinder#findPath(co.aurasphere.algo.
	 * pathfind.Node, co.aurasphere.algo.pathfind.Node)
	 */
	public Node findPath(Node start, Node goal) {
		this.start = start;
		this.target = goal;

		// Unexplored nodes.
		Queue<Node> openList = new PriorityQueue<Node>(this);
		// Explored nodes.
		List<Node> closedList = new ArrayList<Node>();
		// Adds the first node to the "to explore" list.
		openList.add(start);

		// Main loop.
		int currentStep = 0;
		while (!openList.isEmpty() && currentStep < maxNumberOfSteps) {
			Node q = openList.poll();

			mainLoop: for (Node successor : q.getAdjacentNodes()) {

				// If we already explored that node or we already added to the
				// "to explore" list, we just skip it.
				if (closedList.contains(successor)) {
					continue;
				}

				// Stop if we reached the end.
				if (successor.equals(goal)) {
					goal.setParent(successor);
					return goal;
				}

				// If we have already found this node we will keep the best
				// between the two.
				if (openList.contains(successor)) {
					Iterator<Node> iterator = openList.iterator();
					while (iterator.hasNext()) {
						Node oldNode = iterator.next();
						if (oldNode.equals(successor)) {
							// Skip this node if we have a better one.
							if (g(successor) >= g(oldNode)) {
								continue mainLoop;
							}

							// Otherwise, this is a better node let's remove the
							// other one.
							iterator.remove();
						}
					}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Node o1, Node o2) {
		// Use f() function and delegation.
		return Double.compare(f(o1), f(o2));
	}

	/**
	 * Returns the fitness for a node n. The lowest the fitness, the best is the
	 * node.
	 * 
	 * @param n
	 *            the node whose fitness value needs to be computed
	 * @return the fitness score for the node n
	 */
	private double f(Node n) {
		return g(n) + w(n) * h(n);
	}

	/**
	 * Returns the weight of the heuristic function for a node.
	 * 
	 * @param n
	 *            the node whose heuristic weight needs to be computed
	 * @return the weight of the heuristic function for a node
	 */
	private double w(Node n) {
		// Dynamic weight
		if (epsilon != null && estimatedMaxDepth != null)
			return 1 + epsilon - (epsilon * d(n) / estimatedMaxDepth);
		// Static weight
		if (staticWeight != null)
			return staticWeight;
		// No weight
		return 1;
	}

	/**
	 * Returns the depth of the current search.
	 * 
	 * @param n
	 *            the node whose depth needs to be computed
	 * @return the depth of the current search
	 */
	private double d(Node n) {
		// We use g(n) as a depth estimator.
		return g(n);
	}

	/**
	 * Returns the estimated cost to reach the ending node from the node n using
	 * an heuristic.
	 * 
	 * @param n
	 *            the node whose cost to reach the end needs to be estimated
	 * @return the estimated cost to reach the end from the node n
	 */
	private double h(Node n) {
		// Delegate to the metric.
		return hFunction.measureDistance(n, target);
	}

	/**
	 * Returns the cost from the starting node to the node n.
	 * 
	 * @param goal
	 *            the node up to where the distance from start needs to be
	 *            computed
	 * @return the distance between the starting node and the node n
	 */
	private double g(Node goal) {
		return start.cost(goal);
	}

	/**
	 * Sets the {@link #maxNumberOfSteps}.
	 *
	 * @param maxNumberOfSteps
	 *            the new {@link #maxNumberOfSteps}
	 */
	public void setMaxNumberOfSteps(int maxNumberOfSteps) {
		this.maxNumberOfSteps = maxNumberOfSteps;
	}

}