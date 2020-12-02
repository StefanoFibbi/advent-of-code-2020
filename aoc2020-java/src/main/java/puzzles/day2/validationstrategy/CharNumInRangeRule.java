package puzzles.day2.validationstrategy;

import puzzles.day2.PasswordPolicy;
import puzzles.day2.ValidationRule;

public class CharNumInRangeRule implements ValidationRule {
	@Override
	public boolean validate(String password, PasswordPolicy policy) {
		long charOccurrence = password.chars().filter(c -> c == policy.targetChar()).count();
		return charOccurrence >= policy.minOccurrence() && charOccurrence <= policy.maxOccurrence();
	}
}
