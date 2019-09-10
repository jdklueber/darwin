package com.jdklueber.darwinqueens;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessBoardTest {

	@Test
	public void testGet() {
		ChessBoard board = new ChessBoard(8);
		
		assertEquals(false, board.get(1, 1));
		board.put(1, 1);
		assertEquals(true, board.get(1, 1));
	}
	
	@Test
	public void testClearAll() {
		ChessBoard board = new ChessBoard(8);
		board.put(1, 1);
		board.put(3, 3);
		assertTrue(board.get(1, 1));
		assertTrue(board.get(3, 3));
		
		board.clear();
		
		for (int i = 0; i < 64; i++) {
			assertFalse(board.getCell(i));
		}
	}
	
	@Test
	public void testClear() {
		ChessBoard board = new ChessBoard(8);
		assertFalse(board.get(5, 3));
		board.put(5, 3);
		assertTrue(board.get(5, 3));
		board.clear(5,3);
		assertFalse(board.get(5,3));
	}

	@Test
	public void testCountCollisions() {
		ChessBoard board = new ChessBoard(8);
		ChessBoard mask = new ChessBoard(8);
		board.put(2, 2);
		board.put(4,4);
		mask.put(2,2);
		mask.put(6, 6);
		
		assertEquals(1, board.countCollisions(mask));
	}
	
	@Test
	public void testMergeIn() {
		ChessBoard board = new ChessBoard(8);
		ChessBoard mask = new ChessBoard(8);
		board.put(2, 2);
		board.put(4,4);
		mask.put(2,2);
		mask.put(6, 6);
		
		board.mergeIn(mask);
		
		assertTrue(board.get(2, 2));
		assertTrue(board.get(4, 4));
		assertTrue(board.get(6, 6));
	}

	@Test
	public void testGetCellXY() {
		//       0  1  2  3  4  5  6  7
		//  0   00 01 02 03 04 05 06 07 
		//  1   08 09 10 11 12 13 14 15
		//  2   16 17 18 19 20 21 22 23
		//  3   24 25 26 27 28 29 30 31

		ChessBoard board = new ChessBoard(8);
		
		assertEquals(0, board.getCellX(0));
		assertEquals(3, board.getCellX(3));
		assertEquals(7, board.getCellX(7));
		assertEquals(0, board.getCellX(8));

		assertEquals(0, board.getCellY(0));
		assertEquals(0, board.getCellY(4));		
		assertEquals(0, board.getCellY(7));
		assertEquals(1, board.getCellY(8));

		
	}
	
}
