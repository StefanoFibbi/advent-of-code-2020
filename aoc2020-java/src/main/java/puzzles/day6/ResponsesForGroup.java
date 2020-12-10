package puzzles.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ResponsesForGroup {
	final List<String> groupResponses;

	public ResponsesForGroup(List<String> responses) {
		this.groupResponses = responses;
	}

	public Set<Character> getUniqueResponse() {
		Set<Character> responses = new HashSet<>();
		this.groupResponses.forEach(res -> responses.addAll(Arrays.asList(res.chars().mapToObj(c -> (char) c).toArray(Character[]::new))));
		return responses;
	}

	public Set<Character> getResponseEveryoneAnsweredYes() {
		Map<Character, AtomicInteger> charsCount = new HashMap<>();
		this.groupResponses.forEach(res -> {
			List<Character> chars = Arrays.asList(res.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
			chars.forEach(c -> {
				if (charsCount.containsKey(c))
					charsCount.get(c).incrementAndGet();
				else
					charsCount.put(c, new AtomicInteger(1));
			});
		});

		return charsCount
				.entrySet()
				.stream()
				.filter(c -> c.getValue().get() == this.groupResponses.size())
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
	}

}
