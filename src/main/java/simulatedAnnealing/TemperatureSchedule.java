package simulatedAnnealing;

public abstract class TemperatureSchedule {

	// TODO maybe take whether the last move was accepted as input
	public abstract double getTemperature();

	public abstract void updateTemperature(boolean isAccepted);
}
