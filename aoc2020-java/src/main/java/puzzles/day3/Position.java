package puzzles.day3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Position {
	private int currentRow;
	private int currentCol;
}
