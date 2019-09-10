package com.jdklueber.darwinqueens;

import java.util.HashMap;

public class BoardMaskFactory {
	private HashMap<Integer, ChessBoard> boards = new HashMap<>();
	private int size;
	private int cellCount;
	
	public BoardMaskFactory(int size) {
		this.size = size;
		this.cellCount = size * size;
		
		for (int i = 0; i < cellCount; i++) {
			boards.put(i, genMask(i));
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCellCount() {
		return cellCount;
	}
	
	private ChessBoard genMask(int cell) {
		ChessBoard board = new ChessBoard(size);
		
		board.putCell(cell);
		
		int homeX = board.getCellX(cell);
		int homeY = board.getCellY(cell);
		
		fill(board, homeX, homeY, -1,  0);  //Left
		fill(board, homeX, homeY,  1,  0);  //Right
		fill(board, homeX, homeY,  0, -1);  //Up
		fill(board, homeX, homeY,  0,  1);  //Down

		fill(board, homeX, homeY, -1, -1);  //Up Left
		fill(board, homeX, homeY,  1, -1);  //Up Right
		fill(board, homeX, homeY, -1,  1);  //Down Left
		fill(board, homeX, homeY,  1,  1);  //Down Right

		return board;
	}

	
	private void fill(ChessBoard board, int homeX, int homeY, int deltaX, int deltaY) {
		int x = homeX;
		int y = homeY;
		
		while (x >= 0 && x < board.size && y >= 0 && y < board.size) {
			board.put(x, y);
			x += deltaX;
			y += deltaY;
		}
	}
	
	public ChessBoard getMask(int cell) {
		return boards.get(cell);
	}
	
	
	public static void main(String[] args) {
		BoardMaskFactory fac = new BoardMaskFactory(8);

		for (int i = 0; i < fac.getCellCount(); i++) {
			System.out.println(fac.getMask(i));
		}
	}
	
}
