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

import java.util.List;

/**
 * Single node used by this algorithm. The whole data structure is made up by a
 * node element with no parent (the root) connected with others.
 * 
 * @author Donato Rimenti
 */
public interface Node {

	/**
	 * Gets the nodes reachable from this one.
	 *
	 * @return the nodes reachable from this one
	 */
	public List<Node> getAdjacentNodes();

	/**
	 * Gets the parent of this node.
	 *
	 * @return the parent of this node
	 */
	public Node getParent();

	/**
	 * Sets the parent of this node.
	 *
	 * @param parent
	 *            the parent of this node
	 */
	public void setParent(Node parent);

	/**
	 * Returns the real cost to move from this node to the node passed as
	 * argument. <br>
	 * <br>
	 * It is often a good idea to store the value of the distance from the first
	 * node to this one in a field when creating it and returning it if the node
	 * passed is the starting one instead of computing it at each iteration.
	 * <br>
	 * <br>
	 * Here's an example:
	 * 
	 * <pre>
	 * <code>
	 * if (goal.equals(root)) {
	 *     return this.costFromRoot;
	 * } else {
	 *     computeCost(this, goal);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param goal
	 *            the destination node to which the cost needs to be computed
	 * @return the cost of moving from this node to the goal node
	 */
	public double cost(Node goal);

}