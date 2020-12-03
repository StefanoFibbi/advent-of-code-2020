package puzzles.day1;

import java.util.List;

import utils.ExpenseRepairer;
import utils.FileManager;

// https://adventofcode.com/2020/day/1
public class Puzzle {
	private static final int TARGET_EXPENSES_SUM = 2020;

	public static void main(String[] args) {
		List<Integer> expenses = FileManager.readIntegerListFromResource("puzzles/day1/input");

		ExpenseRepairer expenseRepairer = new ExpenseRepairer(expenses, TARGET_EXPENSES_SUM, 2);
		System.out.println("Day 1 - pt. 1: " + expenseRepairer.getFixedResult());

		ExpenseRepairer expenseRepairer2 = new ExpenseRepairer(expenses, TARGET_EXPENSES_SUM, 3);
		System.out.println("Day 1 - pt. 2: " + expenseRepairer2.getFixedResult());
	}

}
