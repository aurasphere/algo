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