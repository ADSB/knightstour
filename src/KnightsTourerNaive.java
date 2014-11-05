import java.util.Random;

public class KnightsTourerNaive implements KnightsTour {

	protected int[][] _board;
	protected int _row;
	protected int _col;
	protected int _iteration;

	public static int BOARD_ROWS = 8;
	public static int BOARD_COLS = 8;

	public static int[][] KNIGHT_RANGE = {
		{-2, -1},
		{-2, 1},
		{2, -1},
		{2, 1},
		{-1, -2},
		{-1, 2},
		{1, -2},
		{1, 2}
	};

	public KnightsTourerNaive() {
		cleanBoard();
	}

	/**
	 * Set each square on the board to UNVISITED.
	 */
	protected void cleanBoard() {
		_board = new int[8][8];
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board[i].length; j++) {
				_board[i][j] = UNVISITED;
			}
		}
	}

	/**
	 * Set each square on the board to UNVISITED.
	 *
	 * @param move Move to be considered
	 *
	 * @return new position after move
	 */
	protected int[] peekMove(int[] move) {
		return new int[] {
			_row + move[0],
			_col + move[1]
		};
	}

	/**
	 * Check if the position is within the bounds of the board.
	 *
	 * @param position Position to be considered
	 *
	 * @return true if the position is within bounds
	 */
	protected boolean isValidPosition(int[] position) {
		return ((position[0] >= 0) && (position[0] < BOARD_ROWS)) && ((position[1] >= 0) && (position[1] < BOARD_COLS));
	}

	/**
	 * Get the value stored at a position on the board.
	 *
	 * @param position Position to be considered
	 *
	 * @return value stored at position
	 */
	protected int getSquare(int[] position) {
		return _board[position[0]][position[1]];
	}

	/**
	 * Update the current square on the board.
	 */
	protected void updatePosition() {
		_board[_row][_col] = ++_iteration;
	}

	/**
	 * Get the number of moves made.
	 *
	 * @return number of moves made
	 */
	public int getIteration() {
		return _iteration;
	}

	@Override
	public void startTour() {
		Random r = new Random();
		startTour(r.nextInt(BOARD_ROWS), r.nextInt(BOARD_COLS));
	}

	@Override
	public void startTour(int startingRow, int startingCol) {
		_row = startingRow;
		_col = startingCol;
		_iteration = 0;
		cleanBoard();
		updatePosition();
	}

	@Override
	public int getCurrentRow() {
		return _row;
	}

	@Override
	public int getCurrentCol() {
		return _col;
	}

	@Override
	public int[][] getPossibleMoves() {
		int[][] possibleMoves = new int[8][2];
		int[] candidate;
		int possible = 0;
		for (int[] row : KNIGHT_RANGE) {
			candidate = peekMove(row);
			if (isValidPosition(candidate) && (getSquare(candidate) == UNVISITED)) {
				possibleMoves[possible++] = row;
			}
		}
		int[][] actualMoves = new int[possible][2];
		for (int i = 0; i < possible; i++) {
			actualMoves[i] = possibleMoves[i];
		}
		return actualMoves;
	}

	@Override
	public int[] makeMove() {
		Random r = new Random();
		int[][] possible = getPossibleMoves();
		if (possible.length > 0) {
			return makeMove(possible[r.nextInt(possible.length)]);
		}
		else {
			return null;
		}
	}

	@Override
	public int[] makeMove(int[] move) {
		_row += move[0];
		_col += move[1];

		updatePosition();

		return new int[] {
			_row,
			_col
		};
	}

	@Override
	public int[][] getBoardState() {
		return _board;
	}

	@Override
	public String getBoardDisplay() {
		String output = "";
		for (int[] row : _board) {
			output += row[0];
			for (int j = 1; j < row.length; j++) {
				output += "\t" + row[j];
				// output += String.format(" -%d", row[j]);
			}
			output += "\n";
		}
		return output;
	}

}
