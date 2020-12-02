package puzzles.day2;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class PasswordPolicy {
	private final int minOccurrence;
	private final int maxOccurrence;
	private final char targetChar;

	public PasswordPolicy(String rule) {
		String[] ruleParts = rule.split(" ");
		this.targetChar = ruleParts[1].charAt(0);

		String[] ruleOccurrenceLimits = ruleParts[0].split("-");
		this.minOccurrence = Integer.parseInt(ruleOccurrenceLimits[0]);
		this.maxOccurrence = Integer.parseInt(ruleOccurrenceLimits[1]);
	}
}
