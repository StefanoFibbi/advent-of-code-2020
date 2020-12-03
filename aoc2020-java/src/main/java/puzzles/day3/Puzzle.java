package puzzles.day3;

import java.util.ArrayList;
import java.util.List;

import utils.FileManager;

// https://adventofcode.com/2020/day/3
public class Puzzle {

	public static void main(String[] args) {
		List<String> input = FileManager.readFromResourcePath("puzzles/day3/input");

		System.out.println("Day 3, pt.1 - Encountered trees: " + solvePart1(input));
		System.out.println("Day 3, pt.2 - Encountered trees: " + solvePart2(input));
	}

	private static int solvePart1(List<String> input) {
		MapTraveller traveller = new MapTraveller(input);
		return traveller.countEncounteredTrees(new TravelPolicy(1, 3));
	}

	private static long solvePart2(List<String> input) {
		MapTraveller traveller = new MapTraveller(input);

		long encTreesNum = 1;
		for (TravelPolicy policy : buildPart2PolicyList()) {
			encTreesNum *= traveller.countEncounteredTrees(policy);
		}

		return encTreesNum;
	}

	private static List<TravelPolicy> buildPart2PolicyList() {
		List<TravelPolicy> policies = new ArrayList<>();
		policies.add(new TravelPolicy(1, 1));
		policies.add(new TravelPolicy(1, 3));
		policies.add(new TravelPolicy(1, 5));
		policies.add(new TravelPolicy(1, 7));
		policies.add(new TravelPolicy(2, 1));
		return policies;
	}

}
