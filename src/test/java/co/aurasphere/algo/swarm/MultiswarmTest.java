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
package co.aurasphere.algo.swarm;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import co.aurasphere.algo.support.MayFailRule;

/**
 * Test for {@link Multiswarm}.
 * 
 * @author Donato Rimenti
 * 
 */
public class MultiswarmTest {

	/**
	 * Rule for handling expected failures. We use this since this test may
	 * actually fail due to bad luck in the random generation.
	 */
	@Rule
	public MayFailRule mayFailRule = new MayFailRule();

	/**
	 * Tests the multiswarm algorithm with a generic problem. The solution is H
	 * = 1080, A = 50 for a total fitness of 1620. Tested with 50 swarms each
	 * with 1000 particles.
	 */
	@Test
	public void testMultiswarmAlgorithm() {
		MultiswarmConfiguration configuration = new MultiswarmConfiguration(new LolFitnessFunction());
		Multiswarm multiswarm = new Multiswarm(configuration);

		// Iterates 1000 times through the main loop and prints the result.
		for (int i = 0; i < 1000; i++) {
			multiswarm.mainLoop();
		}

		System.out.println("[Multiswarm algorithm] Best fitness found: " + multiswarm.getBestFitness() + "[" + multiswarm.getBestPosition()[0]
				+ "," + multiswarm.getBestPosition()[1] + "]");
		Assert.assertEquals(1080, (int) multiswarm.getBestPosition()[0]);
		Assert.assertEquals(50, (int) multiswarm.getBestPosition()[1]);
		Assert.assertEquals(1620, (int) multiswarm.getBestFitness());
	}

	/**
	 * Tests the swarm algorithm with a generic problem. The solution is H =
	 * 1080, A = 50 for a total fitness of 1620. Tested with 1000 particles.
	 */
	@Test
	public void testSwarmAlgorithm() {
		SwarmConfiguration configuration = new SwarmConfiguration(new LolFitnessFunction());
		Swarm swarm = new Swarm(configuration);

		// Iterates 1000 times through the main loop and prints the result.
		for (int i = 0; i < 1000; i++) {
			swarm.mainLoop();
		}

		System.out.println("[Swarm algorithm] Best fitness found: " + swarm.getBestFitness() + "[" + swarm.getBestPosition()[0]
				+ "," + swarm.getBestPosition()[1] + "]");
		Assert.assertEquals(1080, (int) swarm.getBestPosition()[0]);
		Assert.assertEquals(50, (int) swarm.getBestPosition()[1]);
		Assert.assertEquals(1620, (int) swarm.getBestFitness());
	}

}
