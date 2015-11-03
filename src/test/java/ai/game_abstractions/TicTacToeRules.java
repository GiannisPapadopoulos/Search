package ai.game_abstractions;

import ai.game_abstractions.TicTacToeState.BoardSymbols;

/** Implementation of the Rules class for the game of Tic-Tac-Toe */
public class TicTacToeRules
    extends GameRules<TicTacToeState, TicTacToeMove> {

  // Hack to simplify the isTerminal state test
  private TicTacToeEvaluationFunction evaluationFunction = new TicTacToeEvaluationFunction();

  @Override
  public ai.game_abstractions.GameRules.Player getActivePlayer(TicTacToeState state) {
    return state.getActivePlayer();
  }

  @Override
  public boolean isTerminal(TicTacToeState state) {
    if (evaluationFunction.evaluate(state) != 0) {
      return true;
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (state.getSymbol(i, j) == BoardSymbols.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public TicTacToeState applyMove(TicTacToeState state, TicTacToeMove move) {
    BoardSymbols symbolToPlace = getActivePlayer(state) == Player.MAX ? BoardSymbols.X : BoardSymbols.O;
    TicTacToeState copyOfState = state.copy();
    copyOfState.makeMove(symbolToPlace, move.getRow(), move.getColumn());
    return copyOfState;
  }

  @Override
  public double getMaxScore() {
    return 1;
  }

}
