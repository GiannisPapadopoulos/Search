package simulatedAnnealing;

/**
 * Represents a way of generating moves. Should be defined in a way that makes the neighborhood connected,
 * i.e. for any 2 states there is a way of going from one to the other by some sequence of moves.
 * Combining different types of moves is possible if the extend the same subclass of AbstractMove, which
 * will be provided as a parameter of the simulated annealing search
 * 
 * @param <State>
 *        The type of the state representing the problem
 * @param <Move>
 *        The type of the move, parameterized by State
 * 
 * @author Giannis Papadopoulos
 */
public abstract class AbstractMoveGenerator<State extends OptimizationState, Move extends AbstractMove<State>> {

	/**
	 * Returns a move, in the most basic case a uniformly random one. More sophisticated
	 * approaches are also possible, for instance Tabu search
	 * 
	 * @param state
	 *        The state where the move will be applied
	 */
	public abstract Move getRandomMove(State state);
}
