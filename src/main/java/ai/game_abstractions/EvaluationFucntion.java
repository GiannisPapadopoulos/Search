package ai.game_abstractions;


/**
 * The EvaluationFucntion provides a way to determine the value of a state for the first(MAX) player in a game. A
 * requirement is for the game to be zero sum
 */
public abstract class EvaluationFucntion<State extends GameState> {

  /**
   * Returns a value for the specified state from the perspective of the first (MAX) player. The simplest implementation
   * of this method would return the value matching the game rules for terminal states and 0 for the remaining states.
   */
  public abstract double evaluate(State state); 

}
