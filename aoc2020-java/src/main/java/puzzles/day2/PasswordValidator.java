package puzzles.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidator {
	private final ValidationRule validationRule;
	private final PasswordPolicy policy;
	private final String password;

	public PasswordValidator(String pwdWithPolicy, ValidationRule validationRule) {
		this.validationRule = validationRule;

		List<String> parts = Arrays.stream(pwdWithPolicy.split(":")).map(String::trim).collect(Collectors.toList());
		this.policy = new PasswordPolicy(parts.get(0));
		this.password = parts.get(1);
	}

	public boolean validate() {
		return this.validationRule.validate(this.password, this.policy);
	}
}
