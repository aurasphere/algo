package co.aurasphere.algo.pathfind;

/**
 * Interface for a class that can find a path between two nodes.
 * 
 * @author Donato Rimenti
 *
 */
public interface PathFinder {

	/**
	 * Finds the path from start to end. If a path has been found, the end node
	 * is returned. You can get the path between the two nodes by traversing
	 * them back using the {@link Node#getParent()} method. The path returned is
	 * not guaranteed to be the shortest and depends on the implementation.
	 * 
	 * @param start
	 *            the starting node
	 * @param goal
	 *            the ending node
	 * @return the end node if a path has been found, null otherwise
	 */
	public Node findPath(Node start, Node goal);

}
