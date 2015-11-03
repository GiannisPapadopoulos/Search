package ai.game_abstractions;

import ai.game_abstractions.TicTacToeState.BoardSymbols;

/**
 * Evaluation function for tic-tac-toe. Returns 1 for wins for player X, -1 for wins by O and 0 for all other states
 * (including non-terminal)
 */
public class TicTacToeEvaluationFunction
    extends EvaluationFucntion<TicTacToeState> {

  @Override
  public double evaluate(TicTacToeState state) {
    for (int i = 0; i < 3; i++) {
      if (state.getSymbol(i, 0) == BoardSymbols.X && state.getSymbol(i, 1) == BoardSymbols.X
          && state.getSymbol(i, 2) == BoardSymbols.X) {
        return 1;
      } else if (state.getSymbol(i, 0) == BoardSymbols.O && state.getSymbol(i, 1) == BoardSymbols.O
                 && state.getSymbol(i, 2) == BoardSymbols.O) {
        return -1;
      } else if (state.getSymbol(0, i) == BoardSymbols.X && state.getSymbol(1, i) == BoardSymbols.X
                 && state.getSymbol(2, i) == BoardSymbols.X) {
        return 1;
      } else if (state.getSymbol(0, i) == BoardSymbols.O && state.getSymbol(1, i) == BoardSymbols.O
                 && state.getSymbol(2, i) == BoardSymbols.O) {
        return -1;
      }
    }
    if (state.getSymbol(0, 0) == BoardSymbols.X && state.getSymbol(1, 1) == BoardSymbols.X
        && state.getSymbol(2, 2) == BoardSymbols.X) {
      return 1;
    } else if (state.getSymbol(0, 0) == BoardSymbols.O && state.getSymbol(1, 1) == BoardSymbols.O
               && state.getSymbol(2, 2) == BoardSymbols.O) {
      return -1;
    } else if (state.getSymbol(0, 2) == BoardSymbols.X && state.getSymbol(1, 1) == BoardSymbols.X
               && state.getSymbol(2, 0) == BoardSymbols.X) {
      return 1;
    } else if (state.getSymbol(0, 2) == BoardSymbols.O && state.getSymbol(1, 1) == BoardSymbols.O
               && state.getSymbol(2, 0) == BoardSymbols.O) {
      return -1;
    }
    return 0;
  }

}
