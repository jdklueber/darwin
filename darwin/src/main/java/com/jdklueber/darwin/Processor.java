package com.jdklueber.darwin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Processor<T> {
	private DarwinConfiguration<T> config;
	
	public Processor(DarwinConfiguration<T> config) {
		this.config = config;
	}
	
	public T run() {
		List<T> population = new ArrayList<>();
		fillPopulation(population);
		List<ScoredCandidate<T>> scoredPopulation = score(population);
		
		while(!config.getStrategy().endConditionIsMet(scoredPopulation)) {
			population = cull(scoredPopulation);
			population = runCrossovers(population);
			population = mutate(population);
			scoredPopulation = score(population);
		}
		
		return scoredPopulation.get(0).getCandidate();
	}

	private List<T> runCrossovers(List<T> population) {
		List<T> newPopulation = new ArrayList<>();

		for (int i = 0; i < population.size(); i++) {
			newPopulation.add(population.get(i));
			for (int j = 0; j < population.size(); j++) {
				if (i != j) {
					for (int run = 0; run < config.getCrossoverRuns(); run++) {
						newPopulation.addAll(config.getStrategy().crossover(population.get(i), population.get(j)));
					}
				}
			}
		}
		
		return newPopulation;
	}

	private List<T> cull(List<ScoredCandidate<T>> scoredPopulation) {
		List<T> survivors = new ArrayList<>();
		
		for (int i = 0; i < config.getCullTo(); i++) {
			survivors.add(scoredPopulation.get(i).getCandidate());
		}
		
		return survivors;
	}

	private List<T> mutate(List<T> population) {
		List<T> mutants = new ArrayList<>();
		
		for (T candidate : population) {
			mutants.add(candidate);
			for (int i = 0; i < config.getMutantCount(); i++) {
				mutants.add(config.getStrategy().mutate(candidate));
			}
		}
		
		fillPopulation(mutants);
		
		return mutants;
	}

	private List<ScoredCandidate<T>> score(List<T> population) {
		List<ScoredCandidate<T>> scores = new ArrayList<>();
		
		for (T candidate : population) {
			scores.add(new ScoredCandidate<T>(candidate, config.getStrategy().genFitnessScore(candidate)));
		}
		
		Collections.sort(scores);
		Collections.reverse(scores);

		return scores;
	}

	private void fillPopulation(List<T> population) {
		while (population.size() < config.getPopulationSize()) {
			population.add(config.getStrategy().generateRandom());
		}
	}
}
