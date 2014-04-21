package simulatedAnnealing;

/**
 * Abstract class representing the probability that a move will be accepted as a function of
 * the current temperature and the change in the evaluation function.
 * 
 * @author Giannis Papadopoulos
 */
public abstract class AcceptanceStrategy {

	/**
	 * Returns the probability of accepting a move given the current temperature and the
	 * change in the objective function. Typically the probability if 1 if the objective
	 * improved but that is not required
	 * 
	 * @param temperature
	 *        The current temperature
	 * @param delta
	 *        The change of the objective function, positive values mean improvement
	 * @return the probability of accepting the move
	 */
	public abstract double getAcceptanceProbability(double temperature, double delta);

	/**
	 * The metropolis heuristic acceptance strategy
	 */
	public static final AcceptanceStrategy DEFAULT_STRATEGY = new AcceptanceStrategy() {

		@Override
		public double getAcceptanceProbability(double temperature, double delta) {
			if (delta > 0)
				return 1;
			return Math.exp(delta / temperature);
		}
	};

	/** Used to compare the performance of SA to a random walk */
	public static final AcceptanceStrategy ALWAYS_ACCEPT = new AcceptanceStrategy() {

		@Override
		public double getAcceptanceProbability(double temperature, double delta) {
			return 1;
		}
	};
}
