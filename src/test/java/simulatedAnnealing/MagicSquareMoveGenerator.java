package simulatedAnnealing;


public class MagicSquareMoveGenerator
		extends AbstractMoveGenerator<MagicSquareState, MagicSquareMove> {

	@Override
	public MagicSquareMove getRandomMove(MagicSquareState state) {
		int N_squared = state.getN() * state.getN();
		int positionA = (int) (Math.random() * N_squared);
		int positionB = (int) (Math.random() * (N_squared - 1));
		if (positionB >= positionA)
			positionB++;
		return new MagicSquareMove(positionA, positionB);
	}

}
