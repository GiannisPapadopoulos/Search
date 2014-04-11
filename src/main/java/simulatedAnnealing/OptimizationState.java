package simulatedAnnealing;


public abstract class OptimizationState {

	public abstract <S extends OptimizationState> S copy();
}
