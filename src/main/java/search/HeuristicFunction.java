package search;

public abstract class HeuristicFunction<State extends SearchState> {

	public abstract double evaluate(State s);
}
