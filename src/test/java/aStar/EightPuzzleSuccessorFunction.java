package aStar;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import search.SuccessorFunction;
import aStar.EightPuzzleAction.Move;

public class EightPuzzleSuccessorFunction
		extends SuccessorFunction<EightPuzzleState, EightPuzzleAction> {

	@Override
	public Iterable<ImmutableTriple<EightPuzzleState, EightPuzzleAction, Double>> apply(
			EightPuzzleState state) {
		List<ImmutableTriple<EightPuzzleState, EightPuzzleAction, Double>> successors = new ArrayList<>();
		Board.BoardIterator iterator = state.getBoard().new BoardIterator();
		while (iterator.hasNext()) {
			EightPuzzleAction action = new EightPuzzleAction(Move.valueOf(iterator.getMove()));
			Board neighbor = iterator.next();
			successors.add(ImmutableTriple.of(new EightPuzzleState(neighbor), action, 1.0));
		}
		return successors;
	}


}
