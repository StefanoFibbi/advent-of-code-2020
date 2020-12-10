package puzzles.day10;

import java.util.List;

import utils.FileManager;

public class Puzzle {

	public static void main(String[] args) {
		List<String> input = FileManager.readFromResourcePath("puzzles/day10/input");
		System.out.println("Day 10, pt.1 - Min * Max jolts: " + solvePart1(input));
		System.out.println("Day 10, pt.2 - Combinations: " + solvePart2(input));
	}

	private static int solvePart1(List<String> adaptersStr) {
		JoltsAdapterAssembler assembler = new JoltsAdapterAssembler(adaptersStr);
		int devJolts = assembler.getMaxAdapterJolts() + 3;
		assembler.connectAllAdapters();
		assembler.connectDevice(devJolts);

		return assembler.getAdapterUsageByIncrement(1) * assembler.getAdapterUsageByIncrement(3);
	}

	private static long solvePart2(List<String> adaptersStr) {
		JoltsAdapterAssembler assembler = new JoltsAdapterAssembler(adaptersStr);
		int devJolts = assembler.getMaxAdapterJolts() + 3;

		return assembler.countAdaptersCombinations(devJolts);
	}

}
