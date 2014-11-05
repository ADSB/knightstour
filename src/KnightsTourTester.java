public class KnightsTourTester {
	public static void main(String[] args) {
		//create a KnightsTour
		KnightsTour kt = new KnightsTourerWarnsdorff();
		testKT(kt);

		int numToursCompleted = 0;
		int numSquaresVisited = 0;

		for (int iRow = 0; iRow < 8; iRow++) {
			for (int iCol = 0; iCol < 8; iCol++) {
				kt = new KnightsTourerWarnsdorff(); //reinitialize
				try {
					int count = testKT2(kt, iRow, iCol);
					numToursCompleted++;
					numSquaresVisited += count;
				} catch (Exception e) {
					System.err.println("Failed tour starting at " + iRow + " " + iCol);
				}
			}
		}
		System.out.printf("Completed " + numToursCompleted + " tours. Avg squares visited %.1f", (double) numSquaresVisited / numToursCompleted);
	}

	/**
	 * Test basic Knights Tour functionality
	 *
	 * @param kt implementation to test
	 */
	public static void testKT(KnightsTour kt) {
		//kt.initialize();

		int[] result;
		kt.startTour(3, 2);
		//result=kt.makeMove(new int[]{3,2});

		System.err.println("Start at " + kt.getCurrentRow() + ":" + kt.getCurrentCol() + " (Should be 3,2?)");
		System.out.println(kt.getBoardDisplay());

		int[][] posMoves = kt.getPossibleMoves();
		int[][] shouldMatch = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
		System.err.println("Match:" + reportCheckPermutations(posMoves, shouldMatch));


		//specify a move  to 1,1

		result = kt.makeMove(new int[]{-2, -1});
		System.err.println("Now at " + kt.getCurrentRow() + ":" + kt.getCurrentCol() + " ( Should be  1,1)?");
		System.out.println(kt.getBoardDisplay());

		posMoves = kt.getPossibleMoves();
		shouldMatch = new int[][]{{1, 2}, {2, -1}, {-1, 2}}; //not {1,2} as it has already been there
		System.err.println("Match:" + reportCheckPermutations(posMoves, shouldMatch));

		//specify a move  to 0,3
		result = kt.makeMove(new int[]{-1, 2});
		posMoves = kt.getPossibleMoves();
		shouldMatch = new int[][]{{2, -1}, {1, 2}, {2, 1}};
		System.err.println("Now at " + kt.getCurrentRow() + ":" + kt.getCurrentCol() + " (Should be  0,3)?");
		System.out.println(kt.getBoardDisplay());

		System.err.println("Match:" + reportCheckPermutations(posMoves, shouldMatch));


		//now, complete a single tour automatically
		int visitedCount = 3;
		while (visitedCount < 64) {
			int[] selectedMove = null;
			selectedMove = kt.makeMove();

			if (selectedMove == null) {
				System.err.println("Done   at " + kt.getCurrentRow() + ":" + kt.getCurrentCol());
				break;
			}
			else {
				//System.err.print(selectedMove[0]+","+selectedMove[1]+" ");System.err.flush();
				visitedCount++;
			}
		}
		System.err.println("\nEnded at count " + visitedCount);

		//print board state - should contain all moves
		print(kt.getBoardDisplay());

	}

	/**
	 * Test knights tours ability to complete a tour from a starting point
	 *
	 * @param kt       implementation to test
	 * @param startRow row to start at, 0-7
	 * @param startCol col to start at, 0-7
	 * @return number of visited squares on this tour
	 */
	public static int testKT2(KnightsTour kt, int startRow, int startCol) {
		//kt.initialize();

		kt.startTour(startRow, startCol);

		//now, complete automatically
		int visitedCount = 1;
		while (visitedCount < 64) {
			int[] selectedMove = null;
			selectedMove = kt.makeMove();

			if (selectedMove == null) {
				break;
			}
			else {
				visitedCount++;
			}
		}
		return visitedCount;
	}

	/**
	 * Check that the moves in test are a permutation of those in shouldMatch
	 *
	 * @param test        list of moves to check. can be an empty array
	 * @param shouldMatch list of moves to check again. can be an empty array
	 * @return if test contains all of the moves in shouldMatch, in any order
	 */
	private static boolean checkPermutation(int[][] test, int[][] shouldMatch) {
		if (shouldMatch.length != test.length || test == null) return false;
		boolean[] matched = new boolean[shouldMatch.length];
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < shouldMatch.length; j++) {
				//test for a match - if so mark as matched
				if (shouldMatch[j][0] == test[i][0] && shouldMatch[j][1] == test[i][1]) {
					matched[i] = true;
					break;
				}
			}
			if (!matched[i]) return false;
		}

		return true;
	}

	/**
	 * Check set of moves match (any order)
	 *
	 * @param test        moves to test
	 * @param shouldMatch check it matches this set
	 * @return result of comparison, readable format
	 */
	private static String reportCheckPermutations(int[][] test, int[][] shouldMatch) {
		if (test == null) return "null";
		boolean result = checkPermutation(test, shouldMatch);
		if (result) return "OK";

		String had = "";
		for (int[] x : test) had += x[0] + ":" + x[1] + " ";
		String should = "";
		for (int[] x : shouldMatch) should += x[0] + ":" + x[1] + " ";
		return "Fail- Had: " + had + " not: " + should;

	}

	private static void print(String lines) {
		System.err.println(lines);
	}
}