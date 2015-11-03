package ai.game_abstractions;

import lombok.Getter;
import lombok.ToString;

/** Move for the game of Tic-Tac-Toe */
@Getter
@ToString
public class TicTacToeMove
    extends GameMove<TicTacToeState> {

  /** The symbol to place */
  private int row;
  private int column;

  public TicTacToeMove(int row, int column) {
    super();
    this.row = row;
    this.column = column;
  }


}
