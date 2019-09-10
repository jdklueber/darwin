package com.jdklueber.darwinqueens;

import static org.junit.Assert.*;

import org.junit.Test;

public class EightQueensStrategyTest {
	
	@Test
	public void test() {
		ChessBoard board = new ChessBoard(8);
		board.put(0, 2);
		board.put(1, 5);
		board.put(2, 7);
		board.put(3, 0);
		board.put(4, 3);
		board.put(5, 6);
		board.put(6, 4);
		board.put(7, 1);

		EightQueensStrategy strat = new EightQueensStrategy(8);
		System.out.println(board);
		assertEquals(1d, strat.genFitnessScore(board), .000000001d);
	}
	
	@Test
	public void testCollision() {
		BoardMaskFactory fac = new BoardMaskFactory(8);
		ChessBoard mask = fac.getMask(0);
		
		assertTrue(mask.collidesWith(0));
		assertTrue(mask.collidesWith(1));
		assertFalse(mask.collidesWith(62));
	}

}
