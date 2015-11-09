package ai.search_techniques;

import static ai.game_abstractions.TicTacToeState.BoardSymbols.*;

import org.junit.Before;
import org.junit.Test;

import ai.game_abstractions.GameRules.Player;
import ai.game_abstractions.*;
import ai.game_abstractions.TicTacToeState.BoardSymbols;

public class TicTacToeNegamaxTest {

  private TicTacToeRules rules;
  private NegaMax<TicTacToeState, TicTacToeMove, TicTacToeMoveGenerator, TicTacToeRules, TicTacToeEvaluationFunction> negaMax;

  @Before
  public void setup() {
    TicTacToeMoveGenerator moveGenerator = new TicTacToeMoveGenerator();
    rules = new TicTacToeRules();
    TicTacToeEvaluationFunction evalFunction = new TicTacToeEvaluationFunction();
    negaMax = new NegaMax<>(moveGenerator, rules, evalFunction);
  }

  @Test
  public void simpleTest() {
    BoardSymbols[][] board = { { X, O, X }, { O, X, EMPTY }, { O, EMPTY, EMPTY } };
    TicTacToeState currentState = new TicTacToeState(board, Player.MIN);

    // BoardSymbols[][] board = { { X, O, X }, { O, X, X }, { O, EMPTY, EMPTY } };
    // TicTacToeState currentState = new TicTacToeState(board, Player.MIN);


    while (!rules.isTerminal(currentState)) {
      System.out.println(currentState + "\n");
      TicTacToeMove move = negaMax.findBestMove(currentState, 3);
      currentState = rules.applyMove(currentState, move);
    }
    System.out.println(currentState + "\n");

  }

  @Test
  public void testNegamaxTicTacToe() {
    TicTacToeState currentState = TicTacToeState.getInitialState();

    while (!rules.isTerminal(currentState)) {
      System.out.println(currentState + "\n");
      TicTacToeMove move = negaMax.findBestMove(currentState, 9);
      currentState = rules.applyMove(currentState, move);
    }
    System.out.println(currentState + "\n");

  }
}
