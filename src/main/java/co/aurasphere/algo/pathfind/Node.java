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