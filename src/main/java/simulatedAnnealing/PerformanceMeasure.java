package simulatedAnnealing;

public abstract class PerformanceMeasure<State extends OptimizationState> {

	public abstract double getScore(State state);
}
