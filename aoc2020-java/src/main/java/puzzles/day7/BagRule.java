package puzzles.day7;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class BagRule {
	final String bagColor;
	final List<BagRuleItem> containedBags;
}
