package simulatedAnnealing;

import lombok.RequiredArgsConstructor;

/**
 * The temperature is given by k * exp(-lambda * totalAccepted), with k and lambda being parameters
 * and totalAccepted the count of accepted moves since the beginning of the search
 * 
 * @author Giannis Papadopoulos
 */
@RequiredArgsConstructor
public class ExponentialTemperatureSchedule
		extends TemperatureSchedule {

	private final int k;
	private final double lambda;

	/** The number of moves that have been accepted so far */
	private int totalAccepted = 0;

	@Override
	public double getTemperature() {
		return k * Math.exp(-lambda * totalAccepted);
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if (isAccepted)
			totalAccepted++;
	}

}
