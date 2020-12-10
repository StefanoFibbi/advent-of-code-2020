package puzzles.day4;

import java.util.Arrays;
import java.util.List;

import utils.FileManager;

// https://adventofcode.com/2020/day/4
public class Puzzle {

	public static void main(String[] args) {
//		String inputFile = FileManager.readFileFromResourcePathToString("puzzles/day4/example");
		String inputFile = FileManager.readFileFromResourcePathToString("puzzles/day4/input");
		String singleLineInput = inputFile.replace("\n\n", "!").replace("\n", " ");
		List<String> passportsInputList = Arrays.asList(singleLineInput.split("!"));

		PassportBuilder passportBuilder = new PassportBuilder();
		List<Passport> passports = passportBuilder.buildFromList(passportsInputList);

//		System.out.println("Day 4, pt.1 - Valid passports: " + solvePart1(passports));
		System.out.println("Day 4, pt.2 - Encountered trees: " + solvePart2(passports));
	}

	private static int solvePart1(List<Passport> passports) {
		int validPassports = 0;
		PassportValidator validator = new PassportValidator(new NorthPoleCredentials());
		for (Passport p : passports) {
			if (validator.validatePassport(p)) {
				validPassports++;
			}
		}
		return validPassports;
	}

	private static int solvePart2(List<Passport> passports) {
		int validPassports = 0;
		PassportValidator validator = new PassportValidator(new NorthPoleCredentials(), true);
		for (Passport p : passports) {
			if (validator.validatePassport(p)) {
				validPassports++;
			}
		}
		return validPassports;
	}

}
