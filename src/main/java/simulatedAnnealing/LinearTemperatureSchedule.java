package simulatedAnnealing;

public class LinearTemperatureSchedule
		extends TemperatureSchedule {

	private double currentTemp;
	private double diffence;

	public LinearTemperatureSchedule(double T0, double difference) {
		this.currentTemp = T0;
		this.diffence = difference;
	}

	@Override
	public double getTemperature() {
		return currentTemp;
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if (isAccepted)
			currentTemp = Math.max(0, currentTemp - diffence);
	}

}
