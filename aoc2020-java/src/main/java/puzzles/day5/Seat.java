package puzzles.day5;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Seat {
	private int id;
	private int row;
	private int col;
}
