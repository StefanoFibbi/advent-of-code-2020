package puzzles.day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XMasPreamble {

	List<Long> preamble;
	Set<Long> validSums;

	public XMasPreamble(List<Long> values, int preambleSize, int startIdx) {
		this.preamble = new ArrayList<>(values.subList(startIdx, preambleSize + startIdx));
		this.validSums = this.sumPreambleValues();
	}

	private Set<Long> sumPreambleValues() {
		Set<Long> preambleSums = new HashSet<>();
		for (int i = 0; i < this.preamble.size() - 1; i++) {
			for (int j = i + 1; j < this.preamble.size(); j++) {
				Long first = this.preamble.get(i);
				Long second = this.preamble.get(j);
				if (!first.equals(second)) {
					preambleSums.add(first + second);
				}
			}
		}
		return preambleSums;
	}

	public boolean checkValidNum(Long num) {
		return this.validSums.contains(num);
	}

}
