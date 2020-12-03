package puzzles.day3;

import java.util.List;

import lombok.Getter;

public class AreaMap {
	private static final char TREE = '#';
	private static final char EMPTY = '.';
	private final char[][] map;
	@Getter
	private final int maxCols;
	@Getter
	private final int maxRows;

	public AreaMap(List<String> mapScratch) {
		this.maxRows = mapScratch.size();
		this.maxCols = mapScratch.get(0).length();
		this.map = new char[maxRows][maxCols];

		for (int i = 0; i < mapScratch.size(); i++) {
			map[i] = mapScratch.get(i).toCharArray();
		}
	}

	public boolean isTreeByCoord(int row, int col) {
		return this.getAreaInfoByCoord(row, col) == TREE;
	}

	public boolean isEmptyByCoord(int row, int col) {
		return this.getAreaInfoByCoord(row, col) == EMPTY;
	}

	private char getAreaInfoByCoord(int row, int col) {
		return this.map[row % maxRows][col % maxCols];
	}
}
