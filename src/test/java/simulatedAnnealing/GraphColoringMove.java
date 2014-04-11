package simulatedAnnealing;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.stack.TIntStack;
import gnu.trove.stack.array.TIntArrayStack;
import graph.Graph;
import graph.Vertex;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GraphColoringMove
		extends AbstractMove<GraphColoringState> {

	private int vertex;
	private int newColor;

	@Override
	public void apply(GraphColoringState state) {
		performKempeChainMove(state);
	}

	public void performKempeChainMove(GraphColoringState state) {

		Graph<?> graph = state.getGraph();
		TIntList distances = new TIntArrayList(graph.getVertexCount());
		for (int i = 0; i < graph.getVertexCount(); i++)
			distances.add(-1);
		distances.set(vertex, 0);
		int oldColor = state.getColoring().get(vertex);
		TIntStack frontier = new TIntArrayStack();
		TIntSet explored = new TIntHashSet();
		frontier.push(vertex);
		while (frontier.size() > 0) {
			int current = frontier.pop();
			if (!explored.contains(current)) {
				explored.add(current);
				for (Vertex<?> neighbor : graph.getVertex(current).getAdjacentVertexIterator()) {
					int color = state.getColoring().get(neighbor.getID());
					int depth = distances.get(current) + 1;
					if ((color == oldColor && depth > 0 && depth % 2 == 0) || (color == newColor && depth % 2 == 1)) {
						distances.set(neighbor.getID(), depth);
						frontier.push(neighbor.getID());
					}
				}
			}
		}
		TIntIterator iterator = explored.iterator();
		while (iterator.hasNext()) {
			swapColor(state, iterator.next(), oldColor);
		}

		// TODO if a color is no longer used, swap it with another to keep the color indices in [0, k)

	}

	private void swapColor(GraphColoringState state, int v, int oldColor) {
		if (state.getColoring().get(v) == newColor)
			state.getColoring().set(v, oldColor);
		else if (state.getColoring().get(v) == oldColor)
			state.getColoring().set(v, newColor);
		else
			throw new RuntimeException("Invalid Kempe chain " + state.getColoring().get(v) + " " + oldColor + " "
										+ newColor);
	}
}
