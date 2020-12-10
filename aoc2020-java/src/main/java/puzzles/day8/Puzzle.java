package puzzles.day8;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import utils.FileManager;

public class Puzzle {

	public static void main(String[] args) {
		List<String> inputRules = FileManager.readFromResourcePath("puzzles/day8/input");

		System.out.println("Day 8, pt.1 - Accumulator before infinite loop: " + solvePart1(inputRules));
		System.out.println("Day 8, pt.2 - Accumulator before infinite loop: " + solvePart2(inputRules));
	}

	private static Integer solvePart1(List<String> programInstructions) {
		Program program = new Program(programInstructions);

		try {
			program.run();
		}
		catch (InfiniteLoopException ignored) {
		}

		return program.getAccumulator();
	}

	private static Integer solvePart2(List<String> programInstructions) {
		AtomicInteger accumulator = new AtomicInteger();

		Program program = new Program(programInstructions);
		program.bruteForceRun();

		if (program.wholeProgramExecuted())
			accumulator.set(program.getAccumulator());

		return accumulator.get();
	}

}
