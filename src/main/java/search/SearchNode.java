package search;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Node of a search problem
 */
@AllArgsConstructor
@Getter
public class SearchNode<State extends SearchState, Action extends IAction<State>> {

	/** Used to reconstruct the path when the goal is found */
	private SearchNode<State, Action> parent;
	/** The corresponding searchState */
	private State state;
	/** The action leading to that state from the parent */
	private Action action;
	/** The total cost of the path from the start to this node */
	private double cost;

	public boolean isRoot() {
		return parent == null;
	}

}