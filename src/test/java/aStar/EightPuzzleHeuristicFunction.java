package aStar;

import search.HeuristicFunction;

public class EightPuzzleHeuristicFunction
		extends HeuristicFunction<EightPuzzleState> {

	@Override
	public double evaluate(EightPuzzleState state) {
		return state.getBoard()
					.manhattan();
	}

}
