package ai.search_techniques;

import org.apache.commons.lang3.tuple.Pair;

import ai.game_abstractions.*;

/**
 * Genertic implementation of negamax search with alpha-beta pruning. In order to apply to a new game the only
 * requirement is to implement {@link GameState} , {@link GameMove} ,{@link MoveGenerator} ,{@link GameRules} and
 * {@link EvaluationFucntion} for that game
 */
//@formatter:off
public class NegaMax<State extends GameState, 
                     Move extends GameMove<State>,
                     Generator extends MoveGenerator<State, Move>,
                     Rules extends GameRules<State>,
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
    Pair<Move, Double> best = recursiveNegamax(state, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    // TODO Checks for validity?
    return best.getLeft();
  }

  private Pair<Move, Double> recursiveNegamax(State state, int remainingDepth, double alpha, double beta) {
    return null;
  }

}
