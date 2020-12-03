package puzzles.day3;

import java.util.List;

public class MapTraveller {
	private final AreaMap areaMap;

	public MapTraveller(List<String> mapScratch) {
		this.areaMap = new AreaMap(mapScratch);
	}

	public int countEncounteredTrees(TravelPolicy travelPolicy) {
		Position position = new Position(0, 0);
		int encounteredTrees = 0;

		while (position.currentRow() < this.areaMap.getMaxRows()) {
			if (this.areaMap.isTreeByCoord(position.currentRow(), position.currentCol())) {
				encounteredTrees++;
			}

			position.currentRow(position.currentRow() + travelPolicy.nextRowStep());
			position.currentCol(position.currentCol() + travelPolicy.nextColStep());
		}

		return encounteredTrees;
	}




}
