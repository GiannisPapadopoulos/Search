package ai.search_techniques;

import org.apache.commons.lang3.tuple.Pair;

import ai.game_abstractions.*;
import ai.game_abstractions.GameRules.Player;

/**
 * Genertic implementation of negamax search with alpha-beta pruning. In order to apply to a new game the only
 * requirement is to implement {@link GameState} , {@link GameMove} ,{@link MoveGenerator} ,{@link GameRules} and
 * {@link EvaluationFucntion} for that game
 */
//@formatter:off
public class NegaMax<State extends GameState, 
                     Move extends GameMove<State>,
                     Generator extends MoveGenerator<State, Move>,
                     Rules extends GameRules<State, Move>,
                     EvalFunction extends EvaluationFucntion<State>> {
//@formatter:on

  private Generator moveGenerator;
  private Rules rules;
  private EvalFunction evaluationFunction;

  public NegaMax(Generator moveGenerator, Rules rules, EvalFunction evaluationFunction) {
    super();
    this.moveGenerator = moveGenerator;
    this.rules = rules;
    this.evaluationFunction = evaluationFunction;
  }

  /**
   * Returns the best move
   * 
   * @param state
   *        The state to start the search from
   * @param maxDepth
   *        The maximum depth
   * @return
   */
  public Move findBestMove(State state, int maxDepth) {
    if (rules.isTerminal(state)) {
      return null;
    }
    Pair<Double, Move> best = recursiveNegamax(state, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    // TODO Checks for validity?
    return best.getRight();
  }

  boolean DEBUG = false;

  private Pair<Double, Move> recursiveNegamax(State state, int remainingDepth, double alpha, double beta) {
    Player activePlayer = rules.getActivePlayer(state);
    int scoreMultiplier = activePlayer == Player.MAX ? 1 : -1;

    if(rules.isTerminal(state) || remainingDepth == 0) {
      // TODO Constant for NO_MOVE
      double terminalValue = scoreMultiplier * evaluationFunction.evaluate(state);
      return Pair.of(terminalValue, null);
    }
    State clonedState = state.copy();
    // Instead of the typical negation of the opponent's score the score multiplier is used to support games where 
    // the players don't always alternate turns 
    Move bestMove = null;
    double bestScore = Double.NEGATIVE_INFINITY ;
    for(Move move : moveGenerator.getLegalMoves(state)) {
      State newState = rules.applyMove(state, move);
      Pair<Double, Move> result;
      double value;
      if (rules.getActivePlayer(newState) != activePlayer) {
        // Alternating moves
        result = recursiveNegamax(newState, remainingDepth - 1, -beta, -alpha);
        value = -result.getLeft();
        value *= 1;
      } else {
        result = recursiveNegamax(newState, remainingDepth - 1, alpha, beta);
        value = result.getLeft();
      }
      alpha = Math.max(alpha, bestScore);
      if(value > bestScore) {
        bestScore = value;
        bestMove = move;
      }
      // alpha = Math.max(alpha, bestScore);
      if (alpha >= beta) {
        break;
      }
      // Reset the state since we've applied a move
      // TODO if cloning is disabled
      state = clonedState;
    }
    return Pair.of(bestScore, bestMove);
  }

  void debug(State newState, Pair<Double, Move> result) {
    if (rules.isTerminal(newState)) {
      System.out.println(newState);
      System.out.println(result.getLeft() + " active " + rules.getActivePlayer(newState));
      try {
        if (rules.getActivePlayer(newState) == Player.MAX)
          Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  

}
