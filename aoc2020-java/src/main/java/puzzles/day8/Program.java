package puzzles.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

public class Program {
	private final List<Instruction> instructions;
	private final Map<Integer, Instruction> instructionsMap;
	@Getter
	private int accumulator;
	private int currInstructionIdx;

	public Program(List<String> programInputInstructions) {
		this.instructionsMap = new HashMap<>();
		this.instructions = programInputInstructions
				.stream()
				.map(i -> i.split(" "))
				.map(i -> new Instruction(Integer.parseInt(i[1]), i[0]))
				.collect(Collectors.toList());
	}

	public void run() {
		this.currInstructionIdx = 0;
		this.accumulator = 0;
		this.instructionsMap.clear();
		this.runUntilInfiniteLoop();
	}

	public void bruteForceRun() {
		boolean programExecuted = false;
		int currFixIdx = -1;
		Set<String> filters = new HashSet<>(Arrays.asList("jmp", "nop"));
		long maxAttempts = this.instructions.stream().filter(i -> filters.contains(i.operation())).count();
		do {
			try {
				if (currFixIdx >= 0) {
					this.fixInstruction(currFixIdx, filters);
				}

				this.run();
				programExecuted = true;
			}
			catch (InfiniteLoopException ignored) {
				currFixIdx++;
			}
		} while (!programExecuted && currFixIdx < maxAttempts);
	}

	private void runUntilInfiniteLoop() {
		while (this.currInstructionIdx < instructions.size()) {
			if (!this.instructionsMap.containsKey(this.currInstructionIdx)) {
				Instruction instruction = this.instructions.get(this.currInstructionIdx);
				this.instructionsMap.put(this.currInstructionIdx, instruction);
				this.execInstruction(instruction);
			}
			else {
				throw new InfiniteLoopException();
			}
		}
	}

	private void fixInstruction(int currInstructionIdx, Set<String> filters) {
		if (currInstructionIdx > 0) {
			this.fixOpValue(this.getCorruptedInstructionByIndex(currInstructionIdx - 1, filters));
		}

		this.fixOpValue(this.getCorruptedInstructionByIndex(currInstructionIdx, filters));
	}

	private void fixOpValue(Instruction instruction) {
		instruction.operation(instruction.operation().equals("jmp") ? "nop" : "jmp");
	}

	private Instruction getCorruptedInstructionByIndex(int index, Set<String> opFilter) {
		return this.instructions
				.stream()
				.filter(i -> opFilter.contains(i.operation()))
				.collect(Collectors.toList())
				.get(index);
	}

	public boolean wholeProgramExecuted() {
		return this.currInstructionIdx == instructions.size();
	}

	private void execInstruction(Instruction instruction) {
		switch (instruction.operation()) {
			case "acc":
				this.accumulator += instruction.argument();
				this.currInstructionIdx++;
				break;
			case "jmp":
				this.currInstructionIdx += instruction.argument();
				break;
			default:
				this.currInstructionIdx++;
		}
	}
}
