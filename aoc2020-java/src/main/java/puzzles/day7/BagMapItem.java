package puzzles.day7;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class BagMapItem {
	Set<String> containedIn;
	Set<String> containedColors;

	public BagMapItem() {
		this.containedColors = new HashSet<>();
		this.containedIn = new HashSet<>();
	}
}
