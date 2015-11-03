package ai.game_abstractions;

/**
 * Abstraction of the rules for a 2-player zero-sum game. Note that the alternating moves is not a requirement, a player
 * moving twice or more in a row is supported by all search techniques
 */
public abstract class GameRules<State extends GameState, Move extends GameMove<State>> {

  /**
   * Returns the player who is next to act in the specified state. Will not be called by search algorithms in a terminal
   * state
   */
  public abstract Player getActivePlayer(State state);

  /** Returns whether the specified state is terminal, i.e. the game is over */
  public abstract boolean isTerminal(State state);
  
  // TODO Decide if this should always clone the state or also allowed to mutate
  /** Returns the state resulting from applying the specified move */
  public abstract State applyMove(State state, Move move);
  
  /**
   * Returns the best possible score for a player. In games which aren't score based (e.g. the possible outcomes are
   * win/loss/draw this can help speed up the search if a forced win (leading to the max possible score) is found
   */
  public double getMaxScore() {
    return Double.POSITIVE_INFINITY;
  }

  /** The players of zero-sum game */
  public enum Player {
    MAX,
    MIN;
  }
}
