package simulatedAnnealing;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExponentialTemperatureSchedule
		extends TemperatureSchedule {

	private final int k;
	private final double lam;

	private int time = 0;


	@Override
	public double getTemperature() {
		return k * Math.exp((-1) * lam * time);
	}

	@Override
	public void updateTemperature(boolean isAccepted) {
		if (isAccepted)
			time++;
	}

}
