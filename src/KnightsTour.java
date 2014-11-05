
/**
 * Functionality for the KnightsTour program
 * Show the path of a knight, as it tries to visit
 * every square on the chess board.
 */
public interface KnightsTour {
  
	/**
	 * Indicates that a position has not been visited by the knight (yet)
	 */
	public static final int UNVISITED=-1;
	 
	/**
	 * Start the tour at a specified position
	 * @param startingRow row index 0-7 (top-bottom)
	 * @param startingCol column 0-7 (left-right)
	 */
	public void startTour(int startingRow,int startingCol);
	
	/**
	 * Start at a random position
	 */
	public void startTour();
	
	/**
	 * @return row index between 0 (top) and 7 (bottom), incl. 
	 */
	public int getCurrentRow();

	/**
	 * @return column index between 0 (left) and 7 (right), incl.
	 */
	public int getCurrentCol();

	/**
	 * @return an array of possible moves from current position (each in format [delta row,delta col])
	 */
	public int[][] getPossibleMoves();

	/**
	 * Make the next recommended move
	 * @return new position [row,col], or null if no move was possible
	 */
	public int[] makeMove();

	/**
	 * Select the next move
	 * @param move selected move [delta row,delta col]
	 * @return new position [row,col], or null if  move was not possible
	 */
	public int[] makeMove(int[] move);

	/**
	 * @return order in which knight visited each square - UNVISITED indicates 
	 */
	public int[][] getBoardState();
	
	/**
	 * @return an array of Strings describing the board state (order in which knight visited each square, dash for Unvisited spaces)
	 */
	public String getBoardDisplay();
	
}
