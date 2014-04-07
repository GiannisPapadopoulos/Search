package aStar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import lombok.Getter;

public class Board {
	
	private int N;
	@Getter
	private int[][] board;
	private int[] emptySquare;
	private int numNeighbors = 4;
	
	 // construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
    	N = blocks.length;
    	board = clone2DArray(blocks);
    }
    // board dimension N                                       
    public int dimension() {
    	return N;
    }
    // number of blocks out of place
    public int hamming() {
    	int misplaced = 0;
    	for (int i = 0; i<N; i++) {
    		for (int j = 0; j<N; j++) {
    			if (board[i][j] != 0 && board[i][j] != (i * N + j + 1)) 
    				misplaced++;
    		}
    	}
    	return misplaced;
    }
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
    	int sumOfDistances = 0;
    	for (int i = 0; i<N; i++) {
    		for (int j = 0; j<N; j++) {
    			int block = board[i][j];
    			if (block != 0 && block != (i * N + j + 1)) {
    				sumOfDistances += manhattanDistance(correctPosition(block),new int[]{i,j});
    			}
    		}
    	}
    	return sumOfDistances;
    }
    
    private int manhattanDistance(int[] p1, int[] p2) {
    	return Math.abs(p1[0 ] -p2[0]) + Math.abs(p1[1] - p2[1]);
    }
    
    private int[] correctPosition(int block) {
    	if (block == 0) return new int[]{N-1,N-1};
    	int row = (block - 1) / N;
    	int column = (block - 1) % N;
    	return new int[]{row, column};
    }
    
    @SuppressWarnings("unused")
	private boolean misplaced(int block) {
    	int[] pos = correctPosition(block);
    	return board[pos[0]][pos[1]] == block;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
    	for (int i = 0; i<N; i++) {
    		for (int j = 0; j<N; j++) {
    			if (board[i][j] != 0 && board[i][j] != (i * N + j + 1)) 
    				return false;
    		}
    	}
    	return true;
    }
    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
    	int[][] twin = clone2DArray(board);
    	if (twin[0][0] != 0 && twin[0][1] != 0) {
			int temp = twin[0][0];
			twin[0][0] = twin[0][1];
			twin[0][1] = temp;
    	}
    	else {
			int temp = twin[1][0];
			twin[1][0] = twin[1][1];
			twin[1][1] = temp;
    	}
    	return new Board(twin);
    }
    // does this board equal y?
    public boolean equals(Object y) {
    	if (y == null || y.getClass() != this.getClass()) return false;
    	Board other = (Board) y;
    	return Arrays.deepEquals(this.board, other.board);
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
    	return new Neighbors();
    }
    
    private void findEmptySquare() {
    	int neighb = 4;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (board[i][j] == 0) {
    				emptySquare = new int[]{i,j};
    				if (i == 0 || i == N-1) neighb--;
    				if (j == 0 || j == N-1) neighb--;
    			}
    		}
    	}
    	numNeighbors = neighb;
    }
    
    private void move(int[][] blocks, String move) {
    	int i = emptySquare[0], j = emptySquare[1];
    	if (move.equals("LEFT")) {
			blocks[i][j] = blocks[i][j-1];
			blocks[i][j-1] = 0;
    	}
    	else if (move.equals("RIGHT")) {
			blocks[i][j] = blocks[i][j+1];
			blocks[i][j+1] = 0;
    	}
    	else if (move.equals("UP")) {
			blocks[i][j] = blocks[i-1][j];
			blocks[i-1][j] = 0;
    	}
    	else if (move.equals("DOWN")) {
			blocks[i][j] = blocks[i+1][j];
			blocks[i+1][j] = 0;
    	}
    	else
    		throw new RuntimeException("Invalid move");
    }
    
    private class Neighbors implements Iterable<Board> {

		@Override
		public Iterator<Board> iterator() {
			return new BoardIterator();
		}
    }
    
	public class BoardIterator
			implements Iterator<Board> {
    	
    	private int i;
    	private String[] moves;
    	
    	public BoardIterator() {
    		findEmptySquare();
    		moves = new String[numNeighbors];
    		//System.out.println(emptySquare==null);
    		int x = emptySquare[0], y = emptySquare[1], k = -1;
    		if (y > 0) moves[++k] = "LEFT";
    		if (y < N - 1) moves[++k] = "RIGHT";
    		if (x > 0) moves[++k] = "UP";
    		if (x < N - 1) moves[++k] = "DOWN";
    	}

		@Override
		public boolean hasNext() {
			return i < numNeighbors;
		}

		@Override
		public Board next() {
			if (!hasNext())
				throw new NoSuchElementException();
			int [][] newBlocks = clone2DArray(board);
			move(newBlocks, moves[i]);
			i++;
			return new Board(newBlocks);
		}

		public String getMove() {
			return moves[i];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
    	
    }
    
    // string representation of the board (in the output format specified below)
    public String toString() {
    	String result = N + "\n";
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			result = result + board[i][j]+" ";
    		}
    		result += "\n";
    	}
    	return result;
    }
    
    private static int[][] clone2DArray(int[][] array) {
        int rows=array.length ;
        //clone the 'shallow' structure of array
        int[][] newArray =(int[][]) array.clone();
        //clone the 'deep' structure of array
        for(int row=0;row<rows;row++){
            newArray[row]=(int[]) array[row].clone();
        }

        return newArray;
    }
}

