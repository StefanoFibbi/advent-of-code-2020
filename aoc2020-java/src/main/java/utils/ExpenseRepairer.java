package utils;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExpenseRepairer {

	private final List<Integer> expenses;
	private final int targetExpenseSum;
	private final int numOfExpensesToFix;

	public int getFixedResult() {
		int[] expensesIdx = this.searchTargetExpensesIdx(targetExpenseSum);

		if (this.isTargetSum(expensesIdx, targetExpenseSum)) {
			return this.computeFix(expensesIdx);
		}

		throw new NoSuchElementException();
	}

	private int[] searchTargetExpensesIdx(int targetVal) {
		int[] idxArray = this.buildIdxArray(numOfExpensesToFix);
		while (!this.isTargetSum(idxArray, targetVal) && !this.allIdxMax(idxArray)) {
			this.updateIdxArray(idxArray, numOfExpensesToFix - 1);
		}
		return idxArray;
	}

	private int[] buildIdxArray(int size) {
		int[] idxArray = new int[size];
		for (int i = 0; i < size; i++) {
			idxArray[i] = i;
		}
		return idxArray;
	}

	private boolean isTargetSum(int[] idxSet, int targetVal) {
		int sum = 0;
		for (int index : idxSet) {
			sum += this.expenses.get(index);
		}
		return sum == targetVal;
	}

	private int computeFix(int[] idxSet) {
		int fixedVal = 1;
		for (int index : idxSet) {
			fixedVal *= this.expenses.get(index);
		}
		return fixedVal;
	}

	private void updateIdxArray(int[] idxArray, int pos) {
		if (idxArray[pos] < this.expenses.size() - 1) {
			idxArray[pos]++;
			for (int i = pos + 1; i < idxArray.length; i++) {
				if (idxArray[i - 1] < this.expenses.size() - 1) {
					idxArray[i] = idxArray[i - 1] + 1;
				}
			}
		}
		else {
			this.updateIdxArray(idxArray, pos - 1);
		}
	}

	private boolean allIdxMax(int[] idxArray) {
		boolean allMax = true;
		for (int index : idxArray) {
			allMax = allMax && index == this.expenses.size() - 1;
		}
		return allMax;
	}
}
