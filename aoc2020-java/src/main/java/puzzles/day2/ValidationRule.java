package puzzles.day2;

public interface ValidationRule {
	boolean validate(String password, PasswordPolicy policy);
}
