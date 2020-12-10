package puzzles.day7;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class BagRuleItem {
	final String bagColor;
	final Integer quantity;
}
