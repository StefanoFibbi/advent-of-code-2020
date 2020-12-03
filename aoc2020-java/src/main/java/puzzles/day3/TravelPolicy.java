package puzzles.day3;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class TravelPolicy {
	private final int nextRowStep;
	private final int nextColStep;

	public TravelPolicy (int nextRowStep, int nextColStep) {
		this.nextColStep = nextColStep;
		this.nextRowStep = nextRowStep;
	}
}
