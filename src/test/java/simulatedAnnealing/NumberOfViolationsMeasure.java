package simulatedAnnealing;

public class NumberOfViolationsMeasure
		extends PerformanceMeasure<MagicSquareState> {

	@Override
	public double getScore(MagicSquareState state) {
		int violations = 0;
		int N = state.getN();
		int magicSum = N * (N * N + 1) / 2;
		// Rows
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += state.get(i, j);
			}
			if (sum != magicSum)
				violations++;
		}
		// Columns
		for (int j = 0; j < N; j++) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += state.get(i, j);
			}
			if (sum != magicSum)
				violations++;
		}
		// Diagonals
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < N; i++) {
			sumA += state.get(i, i);
			sumB += state.get(i, N - i - 1);
		}
		if (sumA != magicSum)
			violations++;
		if (sumB != magicSum)
			violations++;
		return -violations;
	}

}
