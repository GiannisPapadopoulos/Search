package simulatedAnnealing;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;

@RequiredArgsConstructor
@AllArgsConstructor
//@formatter:off
public class SimulatedAnnealingSearch<State extends OptimizationState, 
									  Move extends AbstractMove<State>,
									  Generator extends AbstractMoveGenerator<State, Move>,
									  Measure extends PerformanceMeasure<State>> {
//@formatter:on
	@NonNull
	private AbstractMoveGenerator<State, Move> generator;
	@NonNull
	private PerformanceMeasure<State> performanceMeasure;
	@NonNull
	private AcceptanceStrategy acceptanceStrategy;
	@NonNull
	private TemperatureSchedule scheduler;

	/** Time in milliseconds */
	@NonNull
	private long allowedTime;

	// @Getter
	// private State bestState;
	// @Getter
	// private double bestScore = Double.NEGATIVE_INFINITY;
	
	private Random random = new Random();
											
	public ImmutablePair<State, Double> search(State initialState) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int timeStep = 0;
		State bestState = initialState;
		State currState = initialState;
		double currentScore = performanceMeasure.getScore(currState);
		double bestScore = currentScore;
		double temperature = scheduler.getTemperature();

		int totalAccepted = 0;
		int impr = 0, avgV = 0;

		while (stopWatch.getTime() < allowedTime && temperature > 0) {
			// temperature <- schedule(t)
			temperature = scheduler.getTemperature();
			timeStep++;

			Move move = generator.getRandomMove(currState);
			if (move == null) {
				// Restart maybe?
				System.out.println("No move");
				break;
			}

			// next <- a randomly selected successor of current
			State nextState = currState.getClone();
			move.apply(nextState);
			// E <- next.VALUE - current.value
			double nextScore = performanceMeasure.getScore(nextState);
			double deltaE = nextScore - currentScore;

			boolean isAccepted = acceptanceStrategy.getAcceptanceProbability(temperature, deltaE) > random.nextDouble();
			if (isAccepted) {
				totalAccepted++;
				currState = nextState;
				currentScore = nextScore;
			}
			if (currentScore > bestScore) {
				impr++;
				bestScore = currentScore;
				bestState = currState.getClone();
			}
			else {
				avgV += nextScore;
			}
			scheduler.updateTemperature(isAccepted);

			if (nextScore == 0) {
				System.out.println("solved " + timeStep + " time " + stopWatch.getTime());
				break;
			}
		}
		System.out.println("% accepted " + (1.0 * totalAccepted / timeStep) + " steps " + timeStep + " temp "
							+ temperature + " impr " + impr);
		System.out.println("acc " + totalAccepted + " avV " + (1.0 * avgV / timeStep));
		return ImmutablePair.of(bestState, bestScore);
	}
}
