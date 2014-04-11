package simulatedAnnealing;

public class GraphColoringMoveGenerator
		extends AbstractMoveGenerator<GraphColoringState, GraphColoringMove> {

	@Override
	public GraphColoringMove getRandomMove(GraphColoringState state) {
		int vertex = Constants.RANDOM.nextInt(state.getGraph().getVertexCount());
		int k = state.getColoring().max() + 1;
		int newColor = Constants.RANDOM.nextInt(k);
		while (k > 1 && newColor == state.getColoring().get(vertex)) {
			newColor = Constants.RANDOM.nextInt(k);
		}
		assert state.getColoring().get(vertex) != newColor;
		return new GraphColoringMove(vertex, newColor);
	}

}
