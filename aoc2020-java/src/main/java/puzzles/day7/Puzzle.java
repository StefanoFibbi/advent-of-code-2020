package puzzles.day7;

import java.util.Arrays;
import java.util.List;

import utils.FileManager;

public class Puzzle {

	public static void main(String[] args) {
		List<String> inputRules = buildInputRulesStrList("puzzles/day7/input");

		TravelBagsManager manager = new TravelBagsManager(inputRules);

		System.out.println(manager.countGivenBagContainedInBags("shiny gold"));
		System.out.println(manager.countBagsInside("shiny gold"));
	}

	private static List<String> buildInputRulesStrList(String path) {
		return Arrays.asList(FileManager
				.readFileFromResourcePathToString(path)
				.replace("\n", "")
				.replace("\r", "")
				.split("\\."));
	}

}
