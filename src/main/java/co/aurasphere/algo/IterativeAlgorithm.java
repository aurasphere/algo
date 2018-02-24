package co.aurasphere.algo;

/**
 * Represents an algorithm that finds a solution by iteratively trying to
 * improve a candidate solution with regard to a given measure of quality.
 * 
 * @author Donato Rimenti
 *
 */
public interface IterativeAlgorithm {

	/**
	 * Main loop of the iterative algorithm. Executes just one iteration.
	 */
	public void mainLoop();
}
