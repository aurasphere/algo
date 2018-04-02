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
import java.util.Random;

import co.aurasphere.algo.IterativeAlgorithm;

/**
 * Represents a collection of {@link Particle}. To use this object, instantiate
 * it and call {@link #mainLoop()} repeatedly for a fixed set of time or for a
 * period of time.
 * 
 * @author Donato Rimenti
 *
 */
public class Swarm implements IterativeAlgorithm {

	/**
	 * The particles of this swarm.
	 */
	private Particle[] particles;

	/**
	 * The best position found within the particles of this swarm.
	 */
	private double[] bestPosition;

	/**
	 * The best fitness score found within the particles of this swarm.
	 */
	private double bestFitness = Double.NEGATIVE_INFINITY;

	/**
	 * This Swarm configuration.
	 */
	private SwarmConfiguration configuration;

	/**
	 * Instantiates a new Swarm.
	 *
	 * @param configuration
	 *            the configuration for this swarm
	 */
	public Swarm(SwarmConfiguration configuration) {
		// Clones the configuration passed as argument to ensure thread safety.
		this.configuration = new SwarmConfiguration(configuration);

		int particlesPerSwarm = configuration.getNumParticle();
		Random randomGenerator = configuration.getRandomGenerator();

		// Initiates the particles with random positions and speeds.
		particles = new Particle[particlesPerSwarm];
		for (int i = 0; i < particlesPerSwarm; i++) {
			double[] initialParticlePosition = { randomGenerator.nextDouble(), randomGenerator.nextDouble() };
			double[] initialParticleSpeed = { randomGenerator.nextDouble(), randomGenerator.nextDouble() };
			particles[i] = new Particle(initialParticlePosition, initialParticleSpeed);
		}
	}

	/**
	 * Main loop of the algorithm. Iterates all {@link #particles} of this
	 * swarm. For each particle, computes the new fitness and checks if a new
	 * best position has been found among itself and the swarm and finally
	 * updates the particle position and speed.
	 */
	@Override
	public void mainLoop() {
		for (Particle particle : particles) {

			double[] particleOldPosition = particle.getPosition().clone();

			// Calculate the particle fitness.
			particle.setFitness(configuration.getFitnessFunction().getFitness(particleOldPosition));

			// Check if a new best position has been found for the particle
			// itself and within the swarm.
			if (particle.getFitness() > particle.getBestFitness()) {
				particle.setBestFitness(particle.getFitness());
				particle.setBestPosition(particleOldPosition);

				if (particle.getFitness() > bestFitness) {
					bestFitness = particle.getFitness();
					bestPosition = particleOldPosition;
				}
			}

			// Updates the particle position by adding the speed to the
			// actual position.
			double[] position = particle.getPosition();
			double[] speed = particle.getSpeed();

			position[0] += speed[0];
			position[1] += speed[1];

			// Updates the particle speed.
			speed[0] = getNewParticleSpeedForIndex(particle, 0);
			speed[1] = getNewParticleSpeedForIndex(particle, 1);
		}
	}

	/**
	 * Computes a new speed for a given particle on a given axis. The new speed
	 * is computed using the formula:
	 * 
	 * <pre>
	 * ({@link SwarmConfiguration#INERTIA_FACTOR} * {@link Particle#getSpeed()}) + 
	 * (({@link SwarmConfiguration#COGNITIVE_WEIGHT} * random(0,1)) * ({@link Particle#getBestPosition()} - {@link Particle#getPosition()})) +
	 * (({@link SwarmConfiguration#SOCIAL_WEIGHT} * random(0,1)) * ({@link Swarm#getBestPosition()} - {@link Particle#getPosition()}))
	 * </pre>
	 *
	 * @param particle
	 *            the particle whose new speed needs to be computed
	 * @param index
	 *            the index of the particle axis whose speeds needs to be
	 *            computed
	 * @return the new speed of the particle passed on the given axis
	 */
	private double getNewParticleSpeedForIndex(Particle particle, int index) {
		return ((configuration.getInertiaFactor() * particle.getSpeed()[index])
				+ (randomizePercentage(configuration.getCognitiveWeight())
						* (particle.getBestPosition()[index] - particle.getPosition()[index]))
				+ (randomizePercentage(configuration.getSocialWeight())
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
	 * Gets the {@link #particles}.
	 *
	 * @return the {@link #particles}
	 */
	public Particle[] getParticles() {
		return particles;
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

	/**
	 * Sets the {@link #bestPosition}.
	 *
	 * @param bestPosition
	 *            the new {@link #bestPosition}
	 */
	void setBestPosition(double[] bestPosition) {
		this.bestPosition = bestPosition;
	}

	/**
	 * Sets the {@link #bestFitness}.
	 *
	 * @param bestFitness
	 *            the new {@link #bestFitness}
	 */
	void setBestFitness(double bestFitness) {
		this.bestFitness = bestFitness;
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
		result = prime * result + Arrays.hashCode(particles);
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
		Swarm other = (Swarm) obj;
		if (Double.doubleToLongBits(bestFitness) != Double.doubleToLongBits(other.bestFitness))
			return false;
		if (!Arrays.equals(bestPosition, other.bestPosition))
			return false;
		if (!Arrays.equals(particles, other.particles))
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
		return "Swarm [particles=" + Arrays.toString(particles) + ", bestPosition=" + Arrays.toString(bestPosition)
				+ ", bestFitness=" + bestFitness + "]";
	}

}
