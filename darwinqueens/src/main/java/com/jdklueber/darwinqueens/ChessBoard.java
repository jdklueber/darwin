package com.jdklueber.darwinqueens;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	boolean[] cells;
	int size;
	int cellCount;
	
	public ChessBoard(int size) {
		this.size = size;
		this.cellCount = size * size;
		cells = new boolean[cellCount];
		clear();
	}
	
	public ChessBoard(ChessBoard candidate) {
		this.cells = new boolean[candidate.cells.length];
		this.size = candidate.size;
		this.cellCount = candidate.cellCount;
		
		System.arraycopy(candidate.cells, 0, this.cells, 0, candidate.cells.length);
	}
	
	public void setCellValue(int cell, boolean value) {
		cells[cell] = value;
	}

	public boolean collidesWith(int cell) {
		return cells[cell] == true;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCellCount() {
		return cellCount;
	}
	
	public int getCellX(int cell) {
		return cell % size;
	}
	
	public int getCellY(int cell) {
		return cell / size;
	}
	
	public boolean getCell(int idx) {
		return cells[idx];
	}
	
	public void putCell(int idx) {
		cells[idx] = true;
	}

	public void clearCell(int idx) {
		cells[idx] = false;
	}

	public void putAll() {
		for (int i = 0; i < cells.length; i++) {
			cells[i] = true;
		}
	}
	
	public void clear() {
		for (int i = 0; i < cells.length; i++) {
			cells[i] = false;
		}
	}
	
	public void swap(int cellA, int cellB) {
		boolean tmp = cells[cellA];
		cells[cellA] = cells[cellB];
		cells[cellB] = tmp;
	}
	
	public List<Integer> getState(boolean state) {
		List<Integer> result = new ArrayList<>();
		
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == state) {
				result.add(i);
			}
		}
		
		return result;
	}
	
	public List<Integer> getCollisionPoints(ChessBoard other) {
		List<Integer> results = new ArrayList<>();
		
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] && other.cells[i]) {
				results.add(i);
			}
		}
		
		return results;
	}
	
	public void put(int x, int y) {
		cells[(y * size) + x] = true;
	}

	public boolean get(int x, int y) {
		return cells[(y * size) + x];
	}

	public void clear(int x, int y) {
		cells[(y * size) + x] = false;
	}
	
	public int countCollisions(ChessBoard board) {
		int count = 0;
		
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == true && board.cells[i] == true) {
				count++;
			}
		}
		
		return count;
	}
	
	public void mergeIn(ChessBoard board) {
		for (int i = 0; i < cells.length; i++) {
			if (board.cells[i] == true) {
				cells[i] = true;
			}
		}
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		StringBuilder border = new StringBuilder();
		border.append("+");
		for (int i = 0; i < size; i++) {
			border.append("----");
		}
		border.setCharAt(border.length() - 1, '+');
		
		out.append(border);
		out.append("\n");
		
		for (int y = 0; y < size; y++) {
			out.append("|");
			for (int x = 0; x < size; x++) {
				if (get(x, y)) {
					out.append(" X ");
				} else {
					out.append("   ");
				}
				out.append("|");
				
			}
			out.append("\n");
			out.append(border);
			out.append("\n");
		}
		
		return out.toString();
	}	
}
