package search;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor()
public class Path<State extends SearchState, Action extends IAction<State>> {

	/**
	 * The starting element and goal element
	 */
	State start, goal;

	/**
	 * The route along which we need to move to reach the target.
	 */
	List<Action> route;

	/**
	 * The cost of the path
	 */
	double cost;

	/**
	 * Returns whether a valid path was found or not.
	 */
	public boolean isValidPath() {
		return route != null;
	}
}
