package com.jdklueber.darwinqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jdklueber.darwin.EvolutionStrategy;
import com.jdklueber.darwin.ScoredCandidate;

public class EightQueensStrategy implements EvolutionStrategy<ChessBoard> {
	BoardMaskFactory masks;
	private int generation = 1;
	private int size;
	
	public EightQueensStrategy(int size) {
		this.size = size;
		masks = new BoardMaskFactory(size);
	}
	
	@Override
	public double genFitnessScore(ChessBoard candidate) {
		int goodQueens = 0;
		List<Integer> queens = candidate.getState(true);
		
		for (int current = 0; current < size; current++) {
			boolean collide = false;
			for (int test = 0; test < size; test++) {
				if (test != current) {
					ChessBoard mask = masks.getMask(queens.get(test));
					if (mask.collidesWith(queens.get(current))) {
						collide = true;
						break;
					}
				}
			}
			
			if (!collide) {
				goodQueens++;
			}
		}
		
		return (double) ((double) goodQueens / (double)size);
	}

	@Override
	public ChessBoard mutate(ChessBoard candidate) {
		Random rng = new Random();
		ChessBoard result = new ChessBoard(candidate);
		
		List<Integer> queens = result.getState(true);
		List<Integer> blanks = result.getState(false);
		
		for (int i = 0; i < 3; i++) {
		
			int queenPick = rng.nextInt(queens.size());
			int queen = queens.get(queenPick);
			queens.remove(queenPick);
			
			int blankPick = rng.nextInt(blanks.size());
			int blank = blanks.get(blankPick);
			blanks.remove(blankPick);

			result.swap(queen, blank);
		}
		
		return result;
	}

	@Override
	public ChessBoard generateRandom() {
		ChessBoard board = new ChessBoard(size);
		
		for (Integer i : pick(board.getSize(), board.getCellCount())) {
			board.putCell(i);
		}
		
		return board;
	}

	private List<Integer> pick(int n, int range) {
		Random rng = new Random();
		List<Integer> bag = new ArrayList<>();
		List<Integer> picks = new ArrayList<>();
		
		for (int i = 0; i < range; i++) {
			bag.add(i);
		}
		
		for (int i = 0; i < n; i++) {
			int pick = rng.nextInt(bag.size());
			picks.add(bag.get(pick));
			bag.remove(pick);
		}
		
		return picks;
	}
	
	@Override
	public boolean endConditionIsMet(List<ScoredCandidate<ChessBoard>> scoredPopulation) {
		System.out.println(generation + ": " + scoredPopulation.get(0).getFitness());
		generation++;
		
		return scoredPopulation.get(0).getFitness() == 1.0d;
	}

	@Override
	public List<ChessBoard> crossover(ChessBoard parentA, ChessBoard parentB) {
		ChessBoard childA = new ChessBoard(size);
		ChessBoard childB = new ChessBoard(size);
	
		List<ChessBoard> results = new ArrayList<>();
		Random rng = new Random();

		List<Integer> parentAQueens = parentA.getState(true);
		List<Integer> parentBQueens = parentA.getState(true);

		List<Integer> collisions = parentA.getCollisionPoints(parentB);
		parentAQueens.removeAll(collisions);
		parentBQueens.removeAll(collisions);
		
		for (Integer i : collisions) {
			childA.putCell(i);
			childB.putCell(i);
		}
		
		for (int i = 0; i < parentAQueens.size(); i++) {
			if (rng.nextBoolean()) {
				childA.putCell(parentAQueens.get(i));
				childB.putCell(parentBQueens.get(i));
			} else {
				childA.putCell(parentBQueens.get(i));
				childB.putCell(parentAQueens.get(i));				
			}
		}
		
		results.add(childA);
		results.add(childB);
		
		return results;
		
	}

}
