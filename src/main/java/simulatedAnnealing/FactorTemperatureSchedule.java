package simulatedAnnealing;

public class FactorTemperatureSchedule
		extends TemperatureSchedule {

	private double currentTemp;
	private double factor;

	public FactorTemperatureSchedule(double T0, double factor) {
		this.currentTemp = T0;
		this.factor = factor;
	}

	@Override
	public double getTemperature() {
		
		return currentTemp;
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if(isAccepted)
			currentTemp *= factor;
	}
}
