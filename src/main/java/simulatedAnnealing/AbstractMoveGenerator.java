package simulatedAnnealing;

public abstract class AbstractMoveGenerator<State extends OptimizationState, Move extends AbstractMove<State>> {

	public abstract Move getRandomMove(State state);
}
