package puzzles.day9;

import java.util.List;
import java.util.stream.Collectors;

import utils.FileManager;

public class Puzzle {

	public static void main(String[] args) {
//		List<String> input = FileManager.readFromResourcePath("puzzles/day9/example");
		List<String> input = FileManager.readFromResourcePath("puzzles/day9/input");
		List<Long> inputValues = input.stream().map(Long::parseLong).collect(Collectors.toList());

		System.out.println("Day 9, pt. 1 - First not valid value: " + solvePart1(inputValues, 25));
		System.out.println("Day 9, pt. 2 - First not valid value: " + solvePart2(inputValues, 25));
	}

	private static Long solvePart1(List<Long> inputValues, final int preambleSize) {
		int startPreambleIdx = 0;
		int firstNotValidValueIdx = -1;
		for (int numToValidateIdx = preambleSize; numToValidateIdx < inputValues.size(); numToValidateIdx++) {
			XMasPreamble preamble = new XMasPreamble(inputValues, preambleSize, startPreambleIdx);
			if (!preamble.checkValidNum(inputValues.get(numToValidateIdx))) {
				firstNotValidValueIdx = numToValidateIdx;
				break;
			}
			startPreambleIdx++;
		}
		return inputValues.get(firstNotValidValueIdx);
	}

	private static Long solvePart2(List<Long> inputValues, final int preambleSize) {
		Long firstNotValidValue = solvePart1(inputValues, preambleSize);

		long minVal = Long.MAX_VALUE;
		long maxVal = Long.MIN_VALUE;
		long sum = Long.MIN_VALUE;

		for (int i = 0; i < inputValues.size() - 1 && sum != firstNotValidValue; i++) {
			sum = inputValues.get(i);
			minVal = Long.MAX_VALUE;
			maxVal = Long.MIN_VALUE;

			for (int j = i + 1; j < inputValues.size() && sum < firstNotValidValue; j++) {
				minVal = Math.min(minVal, inputValues.get(i));
				minVal = Math.min(minVal, inputValues.get(j));
				maxVal = Math.max(maxVal, inputValues.get(i));
				maxVal = Math.max(maxVal, inputValues.get(j));
				sum += inputValues.get(j);
			}
		}

		return minVal + maxVal;
	}
}
