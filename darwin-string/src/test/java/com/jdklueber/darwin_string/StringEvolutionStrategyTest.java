package com.jdklueber.darwin_string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringEvolutionStrategyTest {

	@Test
	public void testHammingDistance() {
		StringEvolutionStrategy strategy = new StringEvolutionStrategy("Hello", .1);
		double fitness = strategy.genFitnessScore("Hello");
		
		assertEquals(1.0d, fitness, .0000000001);
	}

	@Test
	public void testHammingDistanceOffByOne() {
		StringEvolutionStrategy strategy = new StringEvolutionStrategy("Hellohello", .1);
		double fitness = strategy.genFitnessScore("Hellohell1");
		
		assertEquals(0.90d, fitness, .0000000001);
	}
}
