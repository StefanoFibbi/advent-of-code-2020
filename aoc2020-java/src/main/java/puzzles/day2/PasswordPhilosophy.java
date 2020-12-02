package puzzles.day2;

import java.util.List;

import puzzles.day2.validationstrategy.CharAtGivenPositionRule;
import puzzles.day2.validationstrategy.CharNumInRangeRule;
import utils.FileManager;

//	https://adventofcode.com/2020/day/2
public class PasswordPhilosophy {

	public static void main(String[] args) {
		List<String> passwordList = FileManager.readFromResourcePath("puzzles/day2/input");

		System.out.println("Day 2, pt.1 - Valid password num: " + solvePart1(passwordList));
		System.out.println("Day 2, pt.2 - Valid password num: " + solvePart2(passwordList));
	}

	static int solvePart1(List<String> passwordList) {
		return solveByRule(passwordList, new CharNumInRangeRule());
	}

	static int solvePart2(List<String> passwordList) {
		return solveByRule(passwordList, new CharAtGivenPositionRule());
	}

	static int solveByRule(List<String> passwordList, ValidationRule rule) {
		int validPasswordNum = 0;
		for (String pwd : passwordList) {
			PasswordValidator validator = new PasswordValidator(pwd, rule);
			if (validator.validate()) {
				validPasswordNum++;
			}
		}

		return validPasswordNum;
	}

}


