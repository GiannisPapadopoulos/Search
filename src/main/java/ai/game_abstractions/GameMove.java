package ai.game_abstractions;

/** Abstraction of a move in a game, parameterized by a GameState */
public abstract class GameMove<State extends GameState> {

  /** Apply the move by mutating the given state */
  public abstract void apply(State state);
}
