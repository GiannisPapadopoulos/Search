package simulatedAnnealing;

public abstract class AbstractMove<State extends OptimizationState> {

	public abstract void apply(State state);
}
