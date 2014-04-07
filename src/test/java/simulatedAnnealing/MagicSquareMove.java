package simulatedAnnealing;

import java.util.Collections;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MagicSquareMove
		extends AbstractMove<MagicSquareState> {

	private int positionA;
	private int positionB;

	@Override
	public void apply(MagicSquareState state) {
		Collections.swap(state.getNumbers(), positionA, positionB);
	}

}
