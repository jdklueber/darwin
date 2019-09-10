package com.jdklueber.darwin_string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jdklueber.darwin.EvolutionStrategy;
import com.jdklueber.darwin.ScoredCandidate;

public class StringEvolutionStrategy implements EvolutionStrategy<String> {
	public static final int ASCII_RANGE = 256;
	
	private String target;
	private Random rngesus;
	private double mutationChance;
	private int generation = 1;
	
	public StringEvolutionStrategy(String target, double mutationChance) {
		this.target = target;
		this.rngesus = new Random();
		this.mutationChance = mutationChance;
	}
	
	public double genFitnessScore(String candidate) {
		//Based on hamming distance
		
		int hits = 0;
		
		for(int i = 0; i < candidate.length(); i++) {
			if (candidate.charAt(i) == target.charAt(i)) {
				hits++;
			}
		}
		
		return (double) ((double) hits / (double) target.length());
		
	}

	public String mutate(String candidate) {
		StringBuilder mutant = new StringBuilder();
		
		for (int i = 0; i < candidate.length(); i++) {
			char newChar = candidate.charAt(i);
			
			if (rngesus.nextDouble() < mutationChance) {
				newChar = (char) rngesus.nextInt(ASCII_RANGE);
			}
			
			mutant.append(newChar);
		}
				
		return mutant.toString();
	}

	public String generateRandom() {
		StringBuilder rando = new StringBuilder();
		
		for (int i = 0; i < target.length(); i++) {			
			char newChar = (char) rngesus.nextInt(ASCII_RANGE);
			rando.append(newChar);
		}
		
		return rando.toString();
	}

	public boolean endConditionIsMet(List<ScoredCandidate<String>> scores) {
		System.out.print(generation + ": ");
		System.out.println(scores.get(0).getCandidate());
		generation++;
		return scores.get(0).getFitness() == 1d;
	}

	public List<String> crossover(String parentA, String parentB) {
		List<String> results = new ArrayList<>();
		StringBuilder childA = new StringBuilder();
		StringBuilder childB = new StringBuilder();
		Random rng = new Random();
		
		for (int i = 0; i < parentA.length(); i++) {
			if (rng.nextBoolean()) {
				childA.append(parentA.charAt(i));
				childB.append(parentB.charAt(i));
			} else {
				childA.append(parentB.charAt(i));
				childB.append(parentA.charAt(i));				
			}
		}
		
		results.add(childA.toString());
		results.add(childB.toString());
		
		return results;
	}

}
