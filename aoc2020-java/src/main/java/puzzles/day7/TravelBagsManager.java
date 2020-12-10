package puzzles.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class TravelBagsManager {

	final Map<String, BagRule> rules;
	final Map<String, BagMapItem> bagLinks;

	public TravelBagsManager(List<String> travelBagsRulesStr) {
		this.rules = travelBagsRulesStr.stream().map(this::buildBagRule).collect(Collectors.toMap(BagRule::bagColor, bag -> bag));
		this.bagLinks = this.rules.values().stream().collect(Collectors.toMap(BagRule::bagColor, rule -> new BagMapItem()));
		this.rules.values().forEach(this::updateLinks);
	}

	private BagRule buildBagRule(String ruleLine) {
		List<String> mainRuleParts = Arrays.stream(ruleLine.split("contain")).collect(Collectors.toList());
		String bagColor = mainRuleParts.get(0).replace("bags", "").trim();

		if (mainRuleParts.get(1).trim().equals("no other bags")) {
			return new BagRule(bagColor, new ArrayList<>());
		}

		List<String> containedBagsRuleParts = Arrays.asList(mainRuleParts.get(1).split(","));
		List<BagRuleItem> bagRuleItems = containedBagsRuleParts
				.stream()
				.map(String::trim)
				.map(bRule -> bRule.replaceFirst(" ", "-"))
				.map(bRule -> {
					List<String> contains = Arrays.asList(bRule.split("-"));
					String bCol = contains.get(1).replace("bags", "").replace("bag", "").trim();
					return new BagRuleItem(bCol, Integer.parseInt(contains.get(0)));
				})
				.collect(Collectors.toList());

		return new BagRule(bagColor, bagRuleItems);
	}

	private void updateLinks(BagRule rule) {
		BagMapItem bigBagLinks = this.bagLinks.get(rule.bagColor());

		for (BagRuleItem smallBag : rule.containedBags()) {
			bigBagLinks.containedColors().add(smallBag.bagColor());
			this.bagLinks.get(smallBag.bagColor()).containedIn().add(rule.bagColor());

		}
	}

	public int countGivenBagContainedInBags(String color) {
		return this.colorsToRoot(color, new HashSet<>()).size();
	}

	public Set<String> colorsToRoot(String color, Set<String> containedInSet) {
		BagMapItem bigBag = this.bagLinks.get(color);

		if (this.bagLinks.get(color).containedIn().isEmpty()) {
			return new HashSet<>(containedInSet);
		}
		else {
			Set<String> t = new HashSet<>(containedInSet);
			for (String col : bigBag.containedIn()) {
				t.addAll(this.colorsToRoot(col, bigBag.containedIn()));
			}
			return t;
		}
	}

	public long countIns(BagRule rule) {
		if (rule.containedBags().isEmpty()) {
			return 1;
		}
		else {
			long count = 1;
			for (BagRuleItem item : rule.containedBags()) {
				BagRule nestedRule = this.rules.get(item.bagColor());
				count += item.quantity() * this.countIns(nestedRule);
			}
			return count;
		}
	}

	public long countBagsInside(String color) {
		return this.countIns(this.rules.get(color));
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this.bagLinks);
	}
}
