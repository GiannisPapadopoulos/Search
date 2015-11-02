package ai.game_abstractions;

/**
 * Abstraction of the rules for a 2-player zero-sum game. Note that the alternating moves is not a requirement, a player
 * moving twice or more in a row is supported by all search techniques
 */
public abstract class GameRules<State extends GameState> {

  /**
   * Returns the player who is next to act in the specified state. Will not be called by search algorithms in a terminal
   * state
   */
  public abstract Player getActivePlayer(State state);

  /** Returns whether the specified state is terminal, i.e. the game is over */
  public abstract boolean isTerminal(State state);
  
  /** Apply the move by mutating the given state */
  public abstract void apply(State state);

  /** Returns the best possible score for a player */
  public double getMaxScore() {
    return Double.POSITIVE_INFINITY;
  }

  public enum Player {
    MAX,
    MIN;
  }
}
