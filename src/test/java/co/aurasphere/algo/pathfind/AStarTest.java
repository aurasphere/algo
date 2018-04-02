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