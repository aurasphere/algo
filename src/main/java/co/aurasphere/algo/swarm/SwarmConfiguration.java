package co.aurasphere.algo.swarm;

import java.util.Random;

import co.aurasphere.algo.FitnessFunction;

/**
 * Configuration for a {@link Swarm}.
 * 
 * @author Donato Rimenti
 *
 */
public class SwarmConfiguration {

	/**
	 * The inertia factor encourages a {@link Particle} to continue moving in
	 * its current direction. Defaults to 0.729 if not specified.
	 */
	private double inertiaFactor = 0.729;

	/**
	 * The cognitive weight encourages a {@link Particle} to move toward its
	 * historical best-known position. Defaults to 1.49445 if not specified.
	 */
	private double cognitiveWeight = 1.49445;

	/**
	 * The social weight encourages a {@link Particle} to move toward the
	 * best-known position found by any of the particle's {@link Swarm}-mates.
	 * Defaults to 1.49445 if not specified.
	 */
	private double socialWeight = 1.49445;

	/**
	 * Random generator used for the {@link Particle} initial position and speed
	 * updates computations. Defaults to a seedless random generator object if
	 * not specified.
	 */
	private Random randomGenerator = new Random();

	/**
	 * Number of {@link Particle} per {@link Swarm}. Defaults to 1000 if not
	 * specified.
	 */
	private int numParticle = 1000;

	/**
	 * The fitness function used to determine how good is a {@link Particle}.
	 */
	private FitnessFunction fitnessFunction;

	/**
	 * Instantiates a new SwarmConfiguration with default values.
	 * 
	 * @param fitnessFunction
	 *            the {@link #fitnessFunction}
	 */
	public SwarmConfiguration(FitnessFunction fitnessFunction) {
		this.fitnessFunction = fitnessFunction;
	}

	/**
	 * Instantiates a new SwarmConfiguration.
	 *
	 * @param configuration
	 *            the configuration to clone
	 */
	public SwarmConfiguration(SwarmConfiguration configuration) {
		this.inertiaFactor = configuration.inertiaFactor;
		this.cognitiveWeight = configuration.cognitiveWeight;
		this.socialWeight = configuration.socialWeight;
		this.randomGenerator = configuration.randomGenerator;
		this.numParticle = configuration.numParticle;
		this.fitnessFunction = configuration.fitnessFunction;
	}

	/**
	 * Gets the {@link #inertiaFactor}.
	 *
	 * @return the {@link #inertiaFactor}
	 */
	public double getInertiaFactor() {
		return inertiaFactor;
	}

	/**
	 * Sets the {@link #inertiaFactor}.
	 *
	 * @param inertiaFactor
	 *            the new {@link #inertiaFactor}
	 */
	public void setInertiaFactor(double inertiaFactor) {
		this.inertiaFactor = inertiaFactor;
	}

	/**
	 * Gets the {@link #cognitiveWeight}.
	 *
	 * @return the {@link #cognitiveWeight}
	 */
	public double getCognitiveWeight() {
		return cognitiveWeight;
	}

	/**
	 * Sets the {@link #cognitiveWeight}.
	 *
	 * @param cognitiveWeight
	 *            the new {@link #cognitiveWeight}
	 */
	public void setCognitiveWeight(double cognitiveWeight) {
		this.cognitiveWeight = cognitiveWeight;
	}

	/**
	 * Gets the {@link #socialWeight}.
	 *
	 * @return the {@link #socialWeight}
	 */
	public double getSocialWeight() {
		return socialWeight;
	}

	/**
	 * Sets the {@link #socialWeight}.
	 *
	 * @param socialWeight
	 *            the new {@link #socialWeight}
	 */
	public void setSocialWeight(double socialWeight) {
		this.socialWeight = socialWeight;
	}

	/**
	 * Gets the {@link #randomGenerator}.
	 *
	 * @return the {@link #randomGenerator}
	 */
	public Random getRandomGenerator() {
		return randomGenerator;
	}

	/**
	 * Sets the {@link #randomGenerator}.
	 *
	 * @param randomGenerator
	 *            the new {@link #randomGenerator}
	 */
	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	/**
	 * Gets the {@link #numParticle}.
	 *
	 * @return the {@link #numParticle}
	 */
	public int getNumParticle() {
		return numParticle;
	}

	/**
	 * Sets the {@link #numParticle}.
	 *
	 * @param numParticle
	 *            the new {@link #numParticle}
	 */
	public void setNumParticle(int numParticle) {
		this.numParticle = numParticle;
	}

	/**
	 * Gets the {@link #fitnessFunction}.
	 *
	 * @return the {@link #fitnessFunction}
	 */
	public FitnessFunction getFitnessFunction() {
		return fitnessFunction;
	}

	/**
	 * Sets the {@link #fitnessFunction}.
	 *
	 * @param fitnessFunction
	 *            the new {@link #fitnessFunction}
	 */
	public void setFitnessFunction(FitnessFunction fitnessFunction) {
		this.fitnessFunction = fitnessFunction;
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
		temp = Double.doubleToLongBits(cognitiveWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fitnessFunction == null) ? 0 : fitnessFunction.hashCode());
		temp = Double.doubleToLongBits(inertiaFactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numParticle;
		result = prime * result + ((randomGenerator == null) ? 0 : randomGenerator.hashCode());
		temp = Double.doubleToLongBits(socialWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SwarmConfiguration other = (SwarmConfiguration) obj;
		if (Double.doubleToLongBits(cognitiveWeight) != Double.doubleToLongBits(other.cognitiveWeight))
			return false;
		if (fitnessFunction == null) {
			if (other.fitnessFunction != null)
				return false;
		} else if (!fitnessFunction.equals(other.fitnessFunction))
			return false;
		if (Double.doubleToLongBits(inertiaFactor) != Double.doubleToLongBits(other.inertiaFactor))
			return false;
		if (numParticle != other.numParticle)
			return false;
		if (randomGenerator == null) {
			if (other.randomGenerator != null)
				return false;
		} else if (!randomGenerator.equals(other.randomGenerator))
			return false;
		if (Double.doubleToLongBits(socialWeight) != Double.doubleToLongBits(other.socialWeight))
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
		return "SwarmConfiguration [inertiaFactor=" + inertiaFactor + ", cognitiveWeight=" + cognitiveWeight
				+ ", socialWeight=" + socialWeight + ", randomGenerator=" + randomGenerator + ", numParticle="
				+ numParticle + ", fitnessFunction=" + fitnessFunction + "]";
	}

}
