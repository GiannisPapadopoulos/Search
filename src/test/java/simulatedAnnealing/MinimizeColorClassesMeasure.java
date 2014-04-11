package simulatedAnnealing;

import java.util.Arrays;

public class MinimizeColorClassesMeasure
		extends PerformanceMeasure<GraphColoringState> {

	@Override
	public double getScore(GraphColoringState state) {
		int[] counts = new int[state.getGraph().getVertexCount()];
		Arrays.fill(counts, 0);
		for (int c : state.getColoring().toArray()) {
			counts[c]++;
		}
		int squareSum = 0;
		for (int c : counts)
			squareSum += c * c;
		return squareSum;
	}

}
