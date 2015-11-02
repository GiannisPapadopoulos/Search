package ai.game_abstractions;

// TODO Provide context to this class to support move ordering etc
/**
 * Provides a way of generating all legal moves in any State of some 2 player zero-sum game where the players act in
 * turns
 * 
 * @param <State>
 *        Abstraction of the state for some game
 * @param <Move>
 *        Abstraction of a move applicable in State
 */
public abstract class MoveGenerator<State extends GameState, Move extends GameMove<State>> {

  /**
   * Returns an iterable over all legal moves. Note that the interface used is {@link java.lang.Iterable} to allow for
   * lazy move generation
   */
  public abstract Iterable<Move> getLegalMoves(State state);
}
