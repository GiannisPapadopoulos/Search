package search;

public abstract class GoalTest<State extends SearchState> {

	/** Goal-test for a given state */
	public abstract boolean apply(State s);
}
