package simulatedAnnealing;

/**
 * The temperature changes after each accepted move with newTemp = oldTemp * factor
 * 
 * @author Giannis Papadopoulos
 */
public class FactorTemperatureSchedule
		extends TemperatureSchedule {

	private double currentTemperature;
	/** The scaling factor */
	private final double factor;

	public FactorTemperatureSchedule(double T0, double factor) {
		this.currentTemperature = T0;
		this.factor = factor;
	}

	@Override
	public double getTemperature() {
		return currentTemperature;
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if(isAccepted)
			currentTemperature *= factor;
	}
}
