package puzzles.day6;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utils.FileManager;

public class Puzzle {
	public static void main(String[] args) {
		List<ResponsesForGroup> responses = buildGroupsResponseList("puzzles/day6/input");
		solvePart1(responses);
		solvePart2(responses);
	}

	private static void solvePart1(List<ResponsesForGroup> responses) {
		List<Set<Character>> grpUniqueResponses = responses.stream().map(ResponsesForGroup::getUniqueResponse).collect(Collectors.toList());

		int yesResponsesCount = 0;
		for (Set<Character> res : grpUniqueResponses)
			yesResponsesCount += res.size();

		System.out.println(yesResponsesCount);
	}

	private static void solvePart2(List<ResponsesForGroup> responses) {
		List<Set<Character>> grpUniqueResponses = responses.stream().map(ResponsesForGroup::getResponseEveryoneAnsweredYes).collect(Collectors.toList());

		int yesResponsesCount = 0;
		for (Set<Character> res : grpUniqueResponses)
			yesResponsesCount += res.size();

		System.out.println(yesResponsesCount);
	}

	private static List<ResponsesForGroup> buildGroupsResponseList(String inputPath) {
		String inputFile = FileManager.readFileFromResourcePathToString(inputPath);
		String singleLineInput = inputFile.replace("\n\n", "!").replace("\n", " ");

		return Arrays.stream(singleLineInput.split("!"))
				.map(inputLine -> new ResponsesForGroup(Arrays.asList(inputLine.split(" "))))
				.collect(Collectors.toList())
				;
	}
}
