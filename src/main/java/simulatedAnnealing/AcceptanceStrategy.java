package simulatedAnnealing;

/**
 * 
 * @author Giannis Papadopoulos
 */
public abstract class AcceptanceStrategy {

	public abstract double getAcceptanceProbability(double temperature, double delta);

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
