package aStar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import search.IAction;

@AllArgsConstructor
@ToString
public class EightPuzzleAction
		implements IAction<EightPuzzleState> {

	/** A move is defined as moving the empty block in one of the four directions */
	public enum Move {
		LEFT,
		RIGHT,
		UP,
		DOWN;
	}

	@Getter
	private Move move;

}
