package com.jdklueber.darwin;

import lombok.Data;

@Data
public class DarwinConfiguration<T> {
	private int populationSize;
	private EvolutionStrategy<T> strategy; 
	private int mutantCount;
	private int cullTo;
	private int crossoverRuns;
}
