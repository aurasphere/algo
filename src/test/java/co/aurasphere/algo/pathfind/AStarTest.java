package co.aurasphere.algo.pathfind;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link AStar}.
 * 
 * @author Donato Rimenti
 *
 */
public class AStarTest extends BasePathfindingTest {

	/**
	 * Tests the plain algorithm with a possible dataset.
	 */
	@Test
	public void testPlainAStar() {
		AStar solver = new AStar(Metrics2D.CHEBYSHEV_DISTANCE);
		Node solution = mainTestPossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNotNull(solution);
		printSolution(solution, 0, 11);
	}

	/**
	 * Tests the algorithm with a static weight and a possible dataset.
	 */
	@Test
	public void testAStarStaticWeight() {
		AStar solver = new AStar(Metrics2D.CHEBYSHEV_DISTANCE, 50.0);
		Node solution = mainTestPossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNotNull(solution);
		printSolution(solution, 0, 11);
	}

	/**
	 * Tests the algorithm with a dynamic weight and a possible dataset.
	 */
	@Test
	public void testAStarDynamicWeight() {
		AStar solver = new AStar(Metrics2D.CHEBYSHEV_DISTANCE, 10.0, 90);
		Node solution = mainTestPossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNotNull(solution);
		printSolution(solution, 0, 11);
	}

	/**
	 * Tests the plain algorithm with an impossible dataset.
	 */
	@Test
	public void testImpossibleAStar() {
		AStar solver = new AStar(Metrics2D.CHEBYSHEV_DISTANCE);
		Node solution = mainTestImpossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNull(solution);
	}
}