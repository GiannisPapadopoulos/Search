package simulatedAnnealing;

/**
 * Abstract representation of a move
 * 
 * @param <State> Type of the state where the move will be applied
 * @author Giannis Papadopoulos
 */
public abstract class AbstractMove<State extends OptimizationState> {

  /** Apply the move by mutating the given state */
  public abstract void apply(State state);
}
