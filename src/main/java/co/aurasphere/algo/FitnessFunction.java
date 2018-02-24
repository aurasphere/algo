package co.aurasphere.algo;

/**
 * Interface for a function which returns the degree of fitness for some
 * variables to solve a problem.
 * 
 * @author Donato Rimenti
 *
 */
public interface FitnessFunction {

	/**
	 * Returns the fitness of some variables according to a problem solution.
	 * 
	 * @param variables
	 *            the variables whose fitness needs to be computed
	 * @return the fitness of the variables
	 */
	public double getFitness(double[] variables);

}
