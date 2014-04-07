package simulatedAnnealing;

import lombok.val;

import org.junit.Test;

public class MagicSquareSimulatedAnnealing {

	@Test
	public void test() {
		int N = 5;
		MagicSquareState state = MagicSquareState.initRandom(N);
		System.out.println(state);

		TemperatureSchedule schedule = new FactorTemperatureSchedule(30, 0.99999);
		TemperatureSchedule exp = new ExponentialTemperatureSchedule(30, 0.0000009);
		PerformanceMeasure<MagicSquareState> pm = new NumberOfViolationsMeasure();
		System.out.println(pm.getScore(state));
		//@formatter:off
		SimulatedAnnealingSearch<MagicSquareState, MagicSquareMove, 
								 AbstractMoveGenerator<MagicSquareState,MagicSquareMove>, 
								 PerformanceMeasure<MagicSquareState>> SA = 
								 	new SimulatedAnnealingSearch<>(new MagicSquareMoveGenerator(), pm, 
		                                                           AcceptanceStrategy.DEFAULT_STRATEGY,
		                                                           schedule, 10000);
		//@formatter:on
		val result = SA.search(state);

		System.out.println(result.left);
		System.out.println(result.right);
	}

	@Test
	public void testPctSolved() {
		int numTests = 100;
		int solved = 0;
		for (int i = 0; i < numTests; i++) {
			if (i % 10 == 0)
				System.out.println(i);
			int N = 5;
			MagicSquareState state = MagicSquareState.initRandom(N);

			TemperatureSchedule schedule = new FactorTemperatureSchedule(30, 0.99999);
			TemperatureSchedule exp = new ExponentialTemperatureSchedule(30, 0.00001);
			PerformanceMeasure<MagicSquareState> pm = new NumberOfViolationsMeasure();
			//@formatter:off
			SimulatedAnnealingSearch<MagicSquareState, MagicSquareMove, 
									 AbstractMoveGenerator<MagicSquareState,MagicSquareMove>, 
									 PerformanceMeasure<MagicSquareState>> SA = 
									 	new SimulatedAnnealingSearch<>(new MagicSquareMoveGenerator(), pm, 
			                                                           AcceptanceStrategy.DEFAULT_STRATEGY,
			                                                           schedule, 10000);
			//@formatter:on
			val result = SA.search(state);
			if (result.getRight() == 0)
				solved++;
		}
		System.out.println("% solved " + (1.0 * solved / numTests));
	}

}
