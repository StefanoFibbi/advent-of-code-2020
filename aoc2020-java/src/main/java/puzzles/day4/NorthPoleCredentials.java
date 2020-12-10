package puzzles.day4;

public class NorthPoleCredentials implements PassportValidationRule {

	@Override
	public String[] mandatoryFields() {
		return new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
	}

}
