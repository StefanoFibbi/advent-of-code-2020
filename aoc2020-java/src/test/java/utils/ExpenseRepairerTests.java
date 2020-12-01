package utils;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import puzzles.day2.validationstrategy.CharAtGivenPositionRule;
import puzzles.day2.PasswordValidator;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpenseRepairerTests {

	private static final int TARGET_SUM = 2020;

	@Test
	public void givenTheInputSet_whenRepairTwoExpense_thenExpectTheCorrectResult() {
		List<String> str = FileManager.readFromResourcePath("puzzles/day1/input");
		List<Integer> values = str.stream().map(Integer::parseInt).collect(Collectors.toList());

		ExpenseRepairer expenseRepairer = new ExpenseRepairer(values, TARGET_SUM, 2);
		int result = expenseRepairer.getFixedResult();

		int correctValue = 357504;
		assertThat(result).isEqualTo(correctValue);
	}

	@Test
	public void givenTheInputSet_whenRepairThreeExpense_thenExpectTheCorrectResult() {
		List<String> str = FileManager.readFromResourcePath("puzzles/day1/input");
		List<Integer> values = str.stream().map(Integer::parseInt).collect(Collectors.toList());

		ExpenseRepairer expenseRepairer = new ExpenseRepairer(values, TARGET_SUM, 3);
		int result = expenseRepairer.getFixedResult();

		int correctValue = 12747392;
		assertThat(result).isEqualTo(correctValue);
	}

}
