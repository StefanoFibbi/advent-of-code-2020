package puzzles.day10;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JoltsAdapterAssembler {
	List<Integer> adapters;
	int[] adapterPlusUsage = new int[]{0, 0, 0};
	int highestAdapterJolts;

	Map<Integer, Long> leavesFromNodeCount;

	public JoltsAdapterAssembler(List<String> adapters) {
		this.adapters = adapters.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
		this.highestAdapterJolts = 0;
		this.leavesFromNodeCount = new HashMap<>();
	}

	public void connectAllAdapters() {
		for (int adapter : this.adapters) {
			addAdapter(adapter);
		}
	}

	public void connectDevice(int deviceJolts) {
		this.addAdapter(deviceJolts);
	}

	private void addAdapter(int newAdapterJolts) {
		if (newAdapterJolts == this.highestAdapterJolts + 1) {
			this.adapterPlusUsage[0]++;
			this.highestAdapterJolts = newAdapterJolts;
		}
		else if (newAdapterJolts == this.highestAdapterJolts + 3) {
			this.adapterPlusUsage[2]++;
			this.highestAdapterJolts = newAdapterJolts;
		}
		else {
			throw new NotPluggableAdapter();
		}
	}

	public int getMaxAdapterJolts() {
		return Collections.max(this.adapters);
	}

	public int getAdapterUsageByIncrement(int increment) {
		return this.adapterPlusUsage[increment - 1];
	}

	private void buildNodeMapFromJoltsValue(int currentNodeJoltValue, int adapterIdx) {
		long combinations = 0;
		for (int i = 1; i <= 3; i++) {
			if (this.leavesFromNodeCount.containsKey(currentNodeJoltValue + i)) {
				combinations += this.leavesFromNodeCount.get(currentNodeJoltValue + i);
			}
		}

		this.leavesFromNodeCount.put(currentNodeJoltValue, adapterIdx == this.adapters.size() ? 1 : combinations);

		if (adapterIdx >= 0) {
			int nextJoltValue = adapterIdx > 0 ? this.adapters.get(adapterIdx - 1) : 0;
			this.buildNodeMapFromJoltsValue(nextJoltValue, adapterIdx - 1);
		}
	}

	public long countAdaptersCombinations(int fromJolt) {
		this.buildNodeMapFromJoltsValue(fromJolt, this.adapters.size());
		return this.leavesFromNodeCount.get(0);
	}
}
