package puzzles.day5;

import java.util.List;
import java.util.stream.Collectors;

import utils.FileManager;

public class Puzzle {
	public static void main(String[] args) {
		List<String> input = FileManager.readFromResourcePath("puzzles/day5/input");

		System.out.println("Day 5, pt.1 - Highest seat id: " + solvePart1(input));
		System.out.println("Day 5, pt.2 - My seat id: " + solvePart2(input));

		solvePart1(input);
		solvePart2(input);
	}

	private static int solvePart1(List<String> input) {
		int maxSeatId = -1;
		for (String seatNum : input) {
			SeatConverter converter = new SeatConverter(seatNum);
			Seat seat = converter.convert();
			if (maxSeatId < seat.id()) {
				maxSeatId = seat.id();
			}
		}

		return maxSeatId;
	}

	private static int solvePart2(List<String> input) {
		List<Integer> seatIds = input
				.stream()
				.map(seatNum -> {
					SeatConverter converter = new SeatConverter(seatNum);
					return converter.convert();
				})
				.map(Seat::id)
				.sorted()
				.collect(Collectors.toList());

		int i;
		for (i = 0; i < seatIds.size() - 1; i++) {
			int diff = seatIds.get(i + 1) - seatIds.get(i);
			if (diff == 2) {
				break;
			}
		}

		return seatIds.get(i) + 1;
	}
}
