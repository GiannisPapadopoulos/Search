package aStar;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import search.SearchState;

@ToString
@AllArgsConstructor
public class EightPuzzleState
		extends SearchState {

	@Getter
	private Board board;

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(board.getBoard());
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass() != EightPuzzleState.class)
			return false;
		Board other = ((EightPuzzleState) o).getBoard();
		return board.equals(other);
	}

}
