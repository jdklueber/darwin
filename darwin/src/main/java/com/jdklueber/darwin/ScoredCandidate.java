package com.jdklueber.darwin;

import lombok.Data;

@Data
public class ScoredCandidate<T> implements Comparable<ScoredCandidate<T>>{
	private T candidate;
	private double fitness;

	public ScoredCandidate() {
		this.candidate = null;
		this.fitness = -1;
	}
	
	public ScoredCandidate(T candidate, double fitness) {
		this.candidate = candidate;
		this.fitness = fitness;
	}

	@Override
	public int compareTo(ScoredCandidate<T> o) {
		return Double.compare(fitness, o.fitness);
	}

}
