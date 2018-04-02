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
 * Specific fitness function implementation to solve the League of Legends
 * problem. This is the problem statement: <br>
 * <br>
 * In League of Legends, a player's Effective Health when defending against
 * physical damage is given by�E=H(100+A)/100, where H is health and A is
 * armor. Health costs 2.5 gold per unit, and Armor costs 18 gold per unit. You
 * have 3600 gold, and you need to optimize the effectiveness E of your health
 * and armor to survive as long as possible against the enemy team's attacks.
 * How much of each should you buy? <br>
 * <br>
 * 
 * @author Donato Rimenti
 *
 */
public class LolFitnessFunction implements FitnessFunction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.aurasphere.algo.FitnessFunction#getFitness(double[])
	 */
	@Override
	public double getFitness(double[] particlePosition) {

		long health = (long) particlePosition[0];
		long armor = (long) particlePosition[1];

		// No negatives values accepted.
		if (health < 0 && armor < 0) {
			return -(health * armor);
		} else if (health < 0) {
			return health;
		} else if (armor < 0) {
			return armor;
		}

		// Checks if the solution is actually feasible provided our gold.
		double cost = (health * 2.5) + (armor * 18);
		if (cost > 3600) {
			return 3600 - cost;
		} else {
			// Check how good is the solution.
			long fitness = (health * (100 + armor)) / 100;
			return fitness;
		}
	}

}
