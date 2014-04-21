package simulatedAnnealing;


/**
 * Abstraction of the state for an optimization problem
 * 
 * @author Giannis Papadopoulos
 */
public abstract class OptimizationState {

	/** Clones the state */
	public abstract <S extends OptimizationState> S copy();
}
