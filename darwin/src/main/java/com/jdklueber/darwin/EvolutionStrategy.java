package com.jdklueber.darwin;

import java.util.List;

public interface EvolutionStrategy<T> {
	public double genFitnessScore(T candidate);
	public T mutate(T candidate);
	public List<T> crossover(T parentA, T parentB);
	public T generateRandom();
	public boolean endConditionIsMet(List<ScoredCandidate<T>> scoredPopulation);
}
