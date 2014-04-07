package aStar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import search.AstarSearch;
import search.Path;
import search.SearchNode;

public class TestAstar {

	@Test
	public void test() {
		int[][] blocks = { { 0, 1, 3 }, { 4, 2, 5, }, { 7, 8, 6 } };
		int[][] goal = { { 1, 2, 3 }, { 4, 5, 6, }, { 7, 8, 0 } };
		EightPuzzleState initialState = new EightPuzzleState(new Board(blocks));
		EightPuzzleState goalState = new EightPuzzleState(new Board(goal));
		EightPuzzleGoalTest goalTest = new EightPuzzleGoalTest();
		EightPuzzleHeuristicFunction heuristic = new EightPuzzleHeuristicFunction();
		EightPuzzleSuccessorFunction successorFunction = new EightPuzzleSuccessorFunction();
		assertTrue(goalTest.apply(goalState));
		assertFalse(goalTest.apply(initialState));
		assertEquals(heuristic.evaluate(goalState), 0, 0.0001);
		assertEquals(heuristic.evaluate(initialState), 4, 0.0001);
		//@formatter:off
		AstarSearch<EightPuzzleState, EightPuzzleAction, EightPuzzleSuccessorFunction, EightPuzzleHeuristicFunction, 
			EightPuzzleGoalTest> planner = new AstarSearch<>(successorFunction, heuristic, goalTest);
		//@formatter:on
		SearchNode<EightPuzzleState, EightPuzzleAction> result = planner.search(initialState);
		Path<EightPuzzleState, EightPuzzleAction> path = planner.findPath(initialState);
		assertEquals(result.getCost(), 4, 0.0001);
		assertEquals(path.getCost(), 4, 0.0001);
		assertEquals(path.getRoute()
							.size(), 4, 0.0001);
		System.out.println(path.getStart());
		System.out.println(path.getRoute());
		System.out.println(path.getGoal());
		// for (EightPuzzleState state : path.getRoute()) {
		// System.out.println(state.getBoard());
		// }

		int[][] hard = { { 5, 3, 1, 4 }, { 10, 2, 8, 7 }, { 14, 13, 0, 11 }, { 6, 9, 15, 12 } };
		long start = System.currentTimeMillis();
		path = planner.findPath(new EightPuzzleState(new Board(hard)));
		double secs = ((System.currentTimeMillis() - start) / 1000.0);
		System.out.println("time " + secs);

		System.out.println(path.getStart());
		System.out.println(path.getRoute());
		System.out.println(path.getGoal());
	}

}
