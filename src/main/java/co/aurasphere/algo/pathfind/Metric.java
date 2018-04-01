package co.aurasphere.algo.pathfind;

/**
 * Interface for a function which can determine the distance between two
 * {@link Node}.
 * 
 * @author Donato Rimenti
 *
 */
public interface Metric {

	/**
	 * Returns the distance between two nodes.
	 * 
	 * @param start
	 *            the starting node
	 * @param goal
	 *            the goal node
	 * @return the distance between two nodes
	 */
	public double measureDistance(Node start, Node goal);

}
