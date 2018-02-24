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
