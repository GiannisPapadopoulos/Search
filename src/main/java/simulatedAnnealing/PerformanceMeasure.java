package simulatedAnnealing;

/**
 * Class used to abstract an objective function that returns a value for each state
 * 
 * @param <State>
 *        The type representing the problem state
 * 
 * @author Giannis Papadopoulos
 */
public abstract class PerformanceMeasure<State extends OptimizationState> {

	/**
	 * @return The value of the objective function for the given state, higher is better
	 */
	public abstract double getScore(State state);
}
