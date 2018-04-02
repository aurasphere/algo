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

import co.aurasphere.algo.FitnessFunction;

/**
 * Configuration used by {@link Multiswarm}.
 * 
 * @author Donato Rimenti
 *
 */
public class MultiswarmConfiguration extends SwarmConfiguration {

	/**
	 * The global weight encourages a {@link Particle} to move toward the
	 * best-known position found by any particle in any {@link Swarm}. Defaults
	 * to 0.3645 if not specified.
	 */
	private double globalWeight = 0.3645;

	/**
	 * Number of {@link Swarm} managed by the {@link Multiswarm}. Defaults to 50
	 * if not specified.
	 */
	private int numSwarms = 50;

	/**
	 * Instantiates a new MultiswarmConfiguration with default values.
	 * 
	 * @param fitnessFunction
	 *            {@link SwarmConfiguration#SwarmConfiguration(FitnessFunction)}
	 */
	public MultiswarmConfiguration(FitnessFunction fitnessFunction) {
		super(fitnessFunction);
	}

	/**
	 * Instantiates a new MultiswarmConfiguration.
	 *
	 * @param configuration
	 *            the configuration to clone
	 */
	public MultiswarmConfiguration(MultiswarmConfiguration configuration) {
		super(configuration);
		this.globalWeight = configuration.globalWeight;
		this.numSwarms = configuration.numSwarms;
	}

	/**
	 * Gets the {@link #globalWeight}.
	 *
	 * @return the {@link #globalWeight}
	 */
	public double getGlobalWeight() {
		return globalWeight;
	}

	/**
	 * Sets the {@link #globalWeight}.
	 *
	 * @param globalWeight
	 *            the new {@link #globalWeight}
	 */
	public void setGlobalWeight(double globalWeight) {
		this.globalWeight = globalWeight;
	}

	/**
	 * Gets the {@link #numSwarms}.
	 *
	 * @return the {@link #numSwarms}
	 */
	public int getNumSwarms() {
		return numSwarms;
	}

	/**
	 * Sets the {@link #numSwarms}.
	 *
	 * @param numSwarms
	 *            the new {@link #numSwarms}
	 */
	public void setNumSwarms(int numSwarms) {
		this.numSwarms = numSwarms;
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
		temp = Double.doubleToLongBits(globalWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numSwarms;
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
		MultiswarmConfiguration other = (MultiswarmConfiguration) obj;
		if (Double.doubleToLongBits(globalWeight) != Double.doubleToLongBits(other.globalWeight))
			return false;
		if (numSwarms != other.numSwarms)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.swarm.SwarmConfiguration#toString()
	 */
	@Override
	public String toString() {
		return "MultiswarmConfiguration [globalWeight=" + globalWeight + ", numSwarms=" + numSwarms
				+ ", swarmConfiguration=" + super.toString() + "]";
	}

}
