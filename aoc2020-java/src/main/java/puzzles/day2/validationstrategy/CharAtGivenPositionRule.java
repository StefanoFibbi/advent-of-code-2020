package puzzles.day2.validationstrategy;

import puzzles.day2.PasswordPolicy;
import puzzles.day2.ValidationRule;

public class CharAtGivenPositionRule implements ValidationRule {
	@Override
	public boolean validate(String password, PasswordPolicy policy) {
		boolean firstPositionMatch = password.charAt(policy.minOccurrence() - 1) == policy.targetChar();
		boolean secondPositionMatch = password.charAt(policy.maxOccurrence() - 1) == policy.targetChar();
		return firstPositionMatch ^ secondPositionMatch;
	}
}
