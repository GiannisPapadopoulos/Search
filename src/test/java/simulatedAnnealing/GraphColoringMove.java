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

		// System.out.println("before " + state.getColoring() + " " + state.getColoring().max());
		TIntList coloring = state.getColoring();
		int max = coloring.max();
		TIntIterator iterator = explored.iterator();
		while (iterator.hasNext()) {
			swapColor(state, iterator.next(), oldColor);
		}

		// TODO if a color is no longer used, swap it with another to keep the color indices in [0, k)

		if (!coloring.contains(oldColor) && oldColor != max) {
			int toRemove = max;
			// System.out.println("before swap " + coloring);
			for (int i = 0; i < coloring.size(); i++) {
				if (coloring.get(i) == toRemove) {
					coloring.set(i, oldColor);
				}
			}
			// System.out.println("after swap " + coloring);
		}
		// System.out.println("vertex " + vertex + " old " + oldColor + " newC " + newColor);
		// System.out.println("after " + state.getColoring());

		assert new TIntHashSet(coloring).size() == coloring.max() + 1 : (coloring.max() + 1) + " "
																	+ new TIntHashSet(coloring).size() + " " + coloring;
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
