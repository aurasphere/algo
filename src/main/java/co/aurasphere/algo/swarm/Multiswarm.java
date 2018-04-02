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

import java.util.Arrays;

import co.aurasphere.algo.IterativeAlgorithm;

/**
 * Represents a collection of {@link Swarm}. To use this object, instantiate it
 * and call {@link #mainLoop()} repeatedly for a fixed set of time or for a
 * period of time.
 * 
 * @author Donato Rimenti
 *
 */
public class Multiswarm implements IterativeAlgorithm {

	/**
	 * The swarms managed by this multiswarm.
	 */
	private Swarm[] swarms;

	/**
	 * The best position found within all the {@link #swarms}.
	 */
	private double[] bestPosition;

	/**
	 * The best fitness score found within all the {@link #swarms}.
	 */
	private double bestFitness = Double.NEGATIVE_INFINITY;

	/**
	 * The configuration for this multiswarm.
	 */
	private MultiswarmConfiguration configuration;

	/**
	 * Instantiates a new Multiswarm.
	 * 
	 * @param configuration
	 *            the configuration for this multiswarm
	 */
	public Multiswarm(MultiswarmConfiguration configuration) {
		this.configuration = new MultiswarmConfiguration(configuration);
		int numSwarms = configuration.getNumSwarms();

		// Initiates the swarms.
		this.swarms = new Swarm[numSwarms];
		for (int i = 0; i < numSwarms; i++) {
			swarms[i] = new Swarm(configuration);
		}
	}

	/**
	 * Main loop of the algorithm. Iterates all particles of all
	 * {@link #swarms}. For each particle, computes the new fitness and checks
	 * if a new best position has been found among itself, the swarm and all the
	 * swarms and finally updates the particle position and speed.
	 */
	@Override
	public void mainLoop() {
		for (Swarm swarm : swarms) {
			for (Particle particle : swarm.getParticles()) {

				double[] particleOldPosition = particle.getPosition().clone();

				// Calculate the particle fitness.
				particle.setFitness(configuration.getFitnessFunction().getFitness(particleOldPosition));

				// Check if a new best position has been found for the particle
				// itself, within the swarm and the multiswarm.
				if (particle.getFitness() > particle.getBestFitness()) {
					particle.setBestFitness(particle.getFitness());
					particle.setBestPosition(particleOldPosition);

					if (particle.getFitness() > swarm.getBestFitness()) {
						swarm.setBestFitness(particle.getFitness());
						swarm.setBestPosition(particleOldPosition);

						if (swarm.getBestFitness() > bestFitness) {
							bestFitness = swarm.getBestFitness();
							bestPosition = swarm.getBestPosition().clone();
						}

					}
				}

				// Updates the particle position by adding the speed to the
				// actual position.
				double[] position = particle.getPosition();
				double[] speed = particle.getSpeed();

				position[0] += speed[0];
				position[1] += speed[1];

				// Updates the particle speed.
				speed[0] = getNewParticleSpeedForIndex(particle, swarm, 0);
				speed[1] = getNewParticleSpeedForIndex(particle, swarm, 1);
			}
		}
	}

	/**
	 * Computes a new speed for a given particle of a given swarm on a given
	 * axis. The new speed is computed using the formula:
	 * 
	 * <pre>
	 * ({@link SwarmConfiguration#INERTIA_FACTOR} * {@link Particle#getSpeed()}) + 
	 * (({@link SwarmConfiguration#COGNITIVE_WEIGHT} * random(0,1)) * ({@link Particle#getBestPosition()} - {@link Particle#getPosition()})) +
	 * (({@link SwarmConfiguration#SOCIAL_WEIGHT} * random(0,1)) * ({@link Swarm#getBestPosition()} - {@link Particle#getPosition()})) + 
	 * (({@link SwarmConfiguration#GLOBAL_WEIGHT} * random(0,1)) * ({@link #bestPosition} - {@link Particle#getPosition()}))
	 * </pre>
	 *
	 * @param particle
	 *            the particle whose new speed needs to be computed
	 * @param swarm
	 *            the swarm which contains the particle
	 * @param index
	 *            the index of the particle axis whose speeds needs to be
	 *            computed
	 * @return the new speed of the particle passed on the given axis
	 */
	private double getNewParticleSpeedForIndex(Particle particle, Swarm swarm, int index) {
		return ((configuration.getInertiaFactor() * particle.getSpeed()[index])
				+ (randomizePercentage(configuration.getCognitiveWeight())
						* (particle.getBestPosition()[index] - particle.getPosition()[index]))
				+ (randomizePercentage(configuration.getSocialWeight())
						* (swarm.getBestPosition()[index] - particle.getPosition()[index]))
				+ (randomizePercentage(configuration.getGlobalWeight())
						* (bestPosition[index] - particle.getPosition()[index])));
	}

	/**
	 * Returns a random number between 0 and the value passed as argument.
	 *
	 * @param value
	 *            the value to randomize
	 * @return a random value between 0 and the one passed as argument
	 */
	private double randomizePercentage(double value) {
		return configuration.getRandomGenerator().nextDouble() * value;
	}

	/**
	 * Gets the {@link #bestPosition}.
	 *
	 * @return the {@link #bestPosition}
	 */
	public double[] getBestPosition() {
		return bestPosition;
	}

	/**
	 * Gets the {@link #bestFitness}.
	 *
	 * @return the {@link #bestFitness}
	 */
	public double getBestFitness() {
		return bestFitness;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bestFitness);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(bestPosition);
		result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
		result = prime * result + Arrays.hashCode(swarms);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Multiswarm other = (Multiswarm) obj;
		if (Double.doubleToLongBits(bestFitness) != Double.doubleToLongBits(other.bestFitness))
			return false;
		if (!Arrays.equals(bestPosition, other.bestPosition))
			return false;
		if (configuration == null) {
			if (other.configuration != null)
				return false;
		} else if (!configuration.equals(other.configuration))
			return false;
		if (!Arrays.equals(swarms, other.swarms))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Multiswarm [swarms=" + Arrays.toString(swarms) + ", bestPosition=" + Arrays.toString(bestPosition)
				+ ", bestFitness=" + bestFitness + ", configuration=" + configuration + "]";
	}

}
