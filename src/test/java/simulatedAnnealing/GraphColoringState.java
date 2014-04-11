package simulatedAnnealing;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import graph.Graph;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GraphColoringState
		extends OptimizationState {

	private Graph<?> graph;
	private TIntList coloring;

	@Override
	public GraphColoringState copy() {
		return new GraphColoringState(graph, new TIntArrayList(coloring));
	}


}
