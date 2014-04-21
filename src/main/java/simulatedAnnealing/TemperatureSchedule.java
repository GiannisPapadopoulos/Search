package simulatedAnnealing;

/**
 * Abstract class for controlling the temperature during the search
 * 
 * @author Giannis Papadopoulos
 */
public abstract class TemperatureSchedule {

	/** Returns the current temperature */
	public abstract double getTemperature();

	/**
	 * Callback to update the temperature if necessary
	 * 
	 * @param isAccepted whether the last move was accepted or not
	 */
	public abstract void updateTemperature(boolean isAccepted);
}
