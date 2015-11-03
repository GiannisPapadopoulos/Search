package ai.game_abstractions;


/** Abstraction of a game state for a 2-player zero-sum game */
public abstract class GameState {

  // TODO Consider how to implement a choice between providing a copy method and reversing the last move
  // TODO This is not typesafe - extracting to a class can also help with the above point
  /** Clones the state, required by search techniques */
  public abstract <S extends GameState> S copy();

}
