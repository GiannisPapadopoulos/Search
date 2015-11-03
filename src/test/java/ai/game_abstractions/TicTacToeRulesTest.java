package ai.game_abstractions;

import static ai.game_abstractions.TicTacToeState.BoardSymbols.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ai.game_abstractions.GameRules.Player;
import ai.game_abstractions.TicTacToeState.BoardSymbols;

public class TicTacToeRulesTest {

  private TicTacToeRules rules;

  @Before
  public void setUp() {
    rules = new TicTacToeRules();
  }

  @Test
  public void testIsTerminalState() {
    BoardSymbols[][] board = { { X, O, X }, { O, X, O }, { X, EMPTY, EMPTY } };
    TicTacToeState state = new TicTacToeState(board, Player.MAX);
    assertTrue(rules.isTerminal(state));

  }

}
