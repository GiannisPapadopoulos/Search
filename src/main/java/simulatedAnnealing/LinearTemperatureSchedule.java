package simulatedAnnealing;

/**
 * Linearly changing temperature, with newTemp = oldTemp - difference
 * The temperature changes after each move that is accepted
 * 
 * @author Giannis Papadopoulos
 */
public class LinearTemperatureSchedule
		extends TemperatureSchedule {

	private double currentTemperature;
	private final double diffence;

	public LinearTemperatureSchedule(double T0, double difference) {
		this.currentTemperature = T0;
		this.diffence = difference;
	}

	@Override
	public double getTemperature() {
		return currentTemperature;
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if (isAccepted)
			currentTemperature = Math.max(0, currentTemperature - diffence);
	}

}
