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
import java.util.List;

/**
 * Simple node used for testing.
 * 
 * @author Donato Rimenti
 */
public class Simple2DNode extends Node2D {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent of this node.
	 */
	private Node parent;

	/**
	 * The nodes reachable from this one.
	 */
	private List<Node> adjacentNodes;

	/**
	 * The g value of this node.
	 */
	private int g;

	/**
	 * The obstacle set to use for this simulation.
	 */
	private static int[][] obstacleSet;

	/**
	 * Instantiates a new Simple2DNode.
	 *
	 * @param x
	 *            the x coordinate of this node
	 * @param y
	 *            the y coordinate of this node
	 */
	public Simple2DNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Instantiates a new Simple2DNode.
	 *
	 * @param x
	 *            the x coordinate of this node
	 * @param y
	 *            the y coordinate of this node
	 * @param parent
	 *            the parent of this node
	 */
	public Simple2DNode(int x, int y, Simple2DNode parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		parent.adjacentNodes.add(this);
		this.g = parent.g + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.pathfind.Node#getAdjacentNodes()
	 */
	@Override
	public List<Node> getAdjacentNodes() {
		if (adjacentNodes == null) {
			this.adjacentNodes = new ArrayList<Node>();
			// Generates the new nodes lazily.
			// Here's a visual representation of the neighbour nodes
			// of a node X:
			// 1 2 3
			// 4 X 6
			// 7 8 9

			// 1
			if (isValid(x - 1, y + 1)) {
				new Simple2DNode(x - 1, y + 1, this);
			}
			// 2
			if (isValid(x, y + 1)) {
				new Simple2DNode(x, y + 1, this);
			}
			// 3
			if (isValid(x + 1, y + 1)) {
				new Simple2DNode(x + 1, y + 1, this);
			}
			// 4
			if (isValid(x - 1, y)) {
				new Simple2DNode(x - 1, y, this);
			}
			// 6
			if (isValid(x + 1, y)) {
				new Simple2DNode(x + 1, y, this);
			}
			// 7
			if (isValid(x - 1, y - 1)) {
				new Simple2DNode(x - 1, y - 1, this);
			}
			// 8
			if (isValid(x, y - 1)) {
				new Simple2DNode(x, y - 1, this);
			}
			// 9
			if (isValid(x + 1, y - 1)) {
				new Simple2DNode(x + 1, y - 1, this);
			}
		}
		return this.adjacentNodes;
	}

	/**
	 * Checks if a node is valid.
	 *
	 * @param x
	 *            the x of the node to check
	 * @param y
	 *            the y of the node to check
	 * @return true if the node is valid, false otherwise
	 */
	private boolean isValid(int x, int y) {
		// Checks if the node is within the grid boundary.
		if (x < 0 || y < 0 || x > 9 || y > 9) {
			return false;
		}

		// Checks on the obstacle set.
		return obstacleSet[x][y] == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.pathfind.Node#getParent()
	 */
	@Override
	public Node getParent() {
		return this.parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.aurasphere.algo.pathfind.Node#setParent(co.aurasphere.algo.pathfind.
	 * Node)
	 */
	@Override
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.aurasphere.algo.pathfind.Node#cost(co.aurasphere.algo.pathfind.Node)
	 */
	@Override
	public double cost(Node goal) {
		return this.g;
	}

	/**
	 * Sets the {@link #obstacleSet}.
	 *
	 * @param obstacleSet
	 *            the new {@link #obstacleSet}
	 */
	public static void setObstacleSet(int[][] obstacleSet) {
		Simple2DNode.obstacleSet = obstacleSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Point#toString()
	 */
	@Override
	public String toString() {
		return x + " " + y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.geom.Point2D#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Point#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Simple2DNode other = (Simple2DNode) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}