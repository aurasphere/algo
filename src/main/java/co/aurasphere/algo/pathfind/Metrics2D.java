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

/**
 * Utility class which exposes some metrics to deal with points in a 2D space.
 * Any node used with these metrics must extend {@link Node2D}.
 * 
 * @author Donato Rimenti
 *
 */
public class Metrics2D {

	/**
	 * Manhattan distance (or taxicab distance), useful when moving in 4
	 * directions (left, right, top and bottom). Returns the sum of the
	 * absolutes difference between the 2 nodes coordinates on the same axis.
	 */
	public static final Metric MANHATTAN_DISTANCE = new Metric() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * co.aurasphere.algo.pathfind.Metric#measureDistance(co.aurasphere.algo
		 * .pathfind.Node, co.aurasphere.algo.pathfind.Node)
		 */
		@Override
		public double measureDistance(Node start, Node goal) {
			Node2D[] nodes = check2DNode(start, goal);
			return Math.abs(nodes[0].x - nodes[1].x) + Math.abs(nodes[0].y - nodes[1].y);
		}
	};

	/**
	 * Chebyshev distance (or diagonal distance), useful when moving in 8
	 * directions (like the king in the chess game). Returns the maximum of the
	 * absolutes difference between the 2 nodes coordinates on the same axis.
	 */
	public static final Metric CHEBYSHEV_DISTANCE = new Metric() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * co.aurasphere.algo.pathfind.Metric#measureDistance(co.aurasphere.algo
		 * .pathfind.Node, co.aurasphere.algo.pathfind.Node)
		 */
		@Override
		public double measureDistance(Node start, Node goal) {
			Node2D[] nodes = check2DNode(start, goal);
			return Math.max(Math.abs(nodes[0].x - nodes[1].x), Math.abs(nodes[0].y - nodes[1].y));
		}
	};

	/**
	 * Euclidean distance (or Pythagorean metric), useful when moving in any
	 * directions. Returns the value of the distance between the distance
	 * computed using Pythagorean theorem.
	 */
	public static final Metric EUCLIDEAN_DISTANCE = new Metric() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * co.aurasphere.algo.pathfind.Metric#measureDistance(co.aurasphere.algo
		 * .pathfind.Node, co.aurasphere.algo.pathfind.Node)
		 */
		@Override
		public double measureDistance(Node start, Node goal) {
			Node2D[] nodes = check2DNode(start, goal);
			return Math.sqrt(Math.pow(nodes[0].x - nodes[1].x, 2) + Math.pow(nodes[0].y - nodes[1].y, 2));
		}
	};

	/**
	 * Private constructor for utility class.
	 */
	private Metrics2D() {
	}

	/**
	 * Checks that the nodes used by these metrics are {@link Node2D} and, if
	 * they are, casts them to that class.
	 * 
	 * @param start
	 *            the start node
	 * @param goal
	 *            the goal node
	 */
	private static Node2D[] check2DNode(Node start, Node goal) {
		if (!(start instanceof Node2D) || !(goal instanceof Node2D)) {
			throw new IllegalArgumentException("Nodes used by this metric must extend " + Node2D.class.getName());
		}
		// Returns an array of casted elements.
		return new Node2D[] { (Node2D) start, (Node2D) goal };
	}

}