package ai.game_abstractions;

import java.util.ArrayList;
import java.util.List;

import ai.game_abstractions.TicTacToeState.BoardSymbols;

/** Move generator for the game of Tic-Tac-Toe */
public class TicTacToeMoveGenerator
    extends MoveGenerator<TicTacToeState, TicTacToeMove> {

  @Override
  public Iterable<TicTacToeMove> getLegalMoves(TicTacToeState state) {
    List<TicTacToeMove> legalMoves = new ArrayList<TicTacToeMove>();
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        if (state.getSymbol(row, column) == BoardSymbols.EMPTY) {
          legalMoves.add(new TicTacToeMove(row, column));
        }
      }
    }
    return legalMoves;
  }

}
