package co.aurasphere.algo.pathfind;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link Dijkistra}.
 * 
 * @author Donato Rimenti
 *
 */
public class DijkistraTest extends BasePathfindingTest {

	/**
	 * Tests the Dijkistra algorithm with a possible dataset.
	 */
	@Test
	public void testDijkistra() {
		Dijkistra solver = new Dijkistra();
		Node solution = mainTestPossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNotNull(solution);
		printSolution(solution, 0, 11);
	}

	/**
	 * Tests the Dijkistra algorithm with an impossible dataset.
	 */
	@Test
	public void testImpossibleDijkistra() {
		Dijkistra solver = new Dijkistra();
		Node solution = mainTestImpossible(solver);

		// Checks that we found a valid solution.
		Assert.assertNull(solution);
	}

}