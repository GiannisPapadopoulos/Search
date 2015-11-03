package ai.game_abstractions;

import lombok.Getter;
import ai.game_abstractions.GameRules.Player;

/**
 * State for the game Tic-Tac-Toe on a 3x3 board. For simplicity it is assumed that X always plays first and is the max
 * player
 */
public class TicTacToeState
    extends GameState {

  /** Representation of the board for tic-tac-toe */
  @Getter
  private BoardSymbols[][] board;

  @Getter
  private Player activePlayer;

  public TicTacToeState(BoardSymbols[][] board, Player activePlayer) {
    super();
    this.board = board;
    this.activePlayer = activePlayer;
  }
  
  public int getNumRows() {
    return board.length;
  }

  public int getNumColumns() {
    return board[0].length;
  }

  @SuppressWarnings("unchecked")
  @Override
  public TicTacToeState copy() {
    BoardSymbols[][] boardCopy = new BoardSymbols[3][3];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        boardCopy[i][j] = board[i][j];
      }
    }
    return new TicTacToeState(boardCopy, activePlayer);
  }

  Player getOtherPlayer() {
    return activePlayer == Player.MAX ? Player.MIN : Player.MAX;
  }
  
  /** Returns the value of the square in the specified row and column */
  public BoardSymbols getSymbol(int row, int column) {
    return board[row][column];
  }
  
  /** Sets the value at the given row, column of the board to the specified symbol */
  public void makeMove(BoardSymbols symbol, int row, int column) {
    board[row][column] = symbol;
    activePlayer = getOtherPlayer();
  }

  /** Returns the initial state of a tic-tac-toe game with all squares empty */
  public static TicTacToeState getInitialState() {
    BoardSymbols[][] board = new BoardSymbols[3][3];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = BoardSymbols.EMPTY;
      }
    }
    return new TicTacToeState(board, Player.MAX);
  }


  /** The values a board square can have, X, O or empty */
  public enum BoardSymbols {
    X,
    O,
    EMPTY;
  }


  @Override
  public String toString() {
    String boardToString = "";
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        BoardSymbols symbol = getSymbol(i, j);
        String symbolString = symbol == BoardSymbols.EMPTY ? "_" : symbol.toString();
        boardToString += symbolString + " ";
      }
      boardToString += "\n";
    }
    return boardToString;
  }

}
