package search;

import org.apache.commons.lang3.tuple.ImmutableTriple;


public abstract class SuccessorFunction<State extends SearchState, Action extends IAction<State>> {

	/** Returns the successors of s in the form of [state, action, cost] triplets */
	public abstract Iterable<ImmutableTriple<State, Action, Double>> apply(State s);

}
