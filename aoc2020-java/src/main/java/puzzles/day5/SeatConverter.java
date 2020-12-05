package puzzles.day5;

public class SeatConverter {
	private final String seatNum;

	public SeatConverter(String seatNum) {
		this.seatNum = seatNum;
	}

	public Seat convert() {
		char[] seatChars = this.seatNum.toCharArray();
		int[] rowMinMaxIds = {0, 127};
		int[] colMinMaxIds = {0, 7};
		for (int i = 0; i < seatChars.length; i++) {
			if (i < 7) {
				this.updateIdxArray(rowMinMaxIds, seatChars[i]);
			}
			else {
				this.updateIdxArray(colMinMaxIds, seatChars[i]);
			}
		}

		int row = rowMinMaxIds[0];
		int col = colMinMaxIds[0];
		int seatId = row * 8 + col;

		return new Seat().row(row).col(col).id(seatId);
	}

	private void updateIdxArray(int[] arrayToUpdate, char upperLowerPart) {
		int updatedIdx = (int) Math.ceil((double) (arrayToUpdate[1] - arrayToUpdate[0]) / 2);

		// Lower half
		if (upperLowerPart == 'F' || upperLowerPart == 'L') {
			arrayToUpdate[1] -= updatedIdx;
		}

		// Upper half
		else {
			arrayToUpdate[0] += updatedIdx;
		}
	}

}
