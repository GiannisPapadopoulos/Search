package simulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class MagicSquareState
		extends OptimizationState {

	private List<Integer> numbers;
	private int N;

	public MagicSquareState(List<Integer> numbers) {
		assert Math.sqrt(numbers.size()) - (int) Math.sqrt(numbers.size()) < 0.00001;
		N = (int) Math.round(Math.sqrt(numbers.size()));
		this.numbers = numbers;
	}

	public int get(int i, int j) {
		return numbers.get(i * N + j);
	}

	@Override
	public MagicSquareState copy() {
		return new MagicSquareState(new ArrayList<Integer>(numbers));
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int index = i * N + j;
				result += (numbers.get(index) + " ");
				if (numbers.get(index) < 10)
					result += " ";
			}
			result += "\n";
		}
		return result;
	}

	public static MagicSquareState initRandom(int N) {
		List<Integer> numbers = new ArrayList<Integer>(N * N);
		for (int i = 0; i < N * N; i++)
			numbers.add(i + 1);
		Collections.shuffle(numbers);
		return new MagicSquareState(numbers);
	}

}
