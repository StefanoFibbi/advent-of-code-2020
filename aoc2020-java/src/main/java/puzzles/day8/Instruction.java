package puzzles.day8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Instruction {
	private final int argument;
	private String operation;

	@Override
	public String toString() {
		return "Instruction{" +
				"argument=" + argument +
				", operation='" + operation + '\'' +
				'}';
	}
}
