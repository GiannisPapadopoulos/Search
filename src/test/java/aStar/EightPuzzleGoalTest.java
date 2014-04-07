package aStar;

import search.GoalTest;

public class EightPuzzleGoalTest
		extends GoalTest<EightPuzzleState> {

	@Override
	public boolean apply(EightPuzzleState state) {
		return state.getBoard()
					.isGoal();
	}

}
