import java.util.Scanner;

public class KnightsTester {

	public static void main(String[] args) {

		KnightsTourerWarnsdorff warnsdorff = new KnightsTourerWarnsdorff();

		Scanner scanner = new Scanner(System.in);

		int highscore = 0;

		while (scanner.hasNextLine()) {

			warnsdorff.startTour();

			while (warnsdorff.getPossibleMoves().length > 0) {
				warnsdorff.makeMove();
				System.out.println(warnsdorff.getBoardDisplay());
			}

			if (warnsdorff.getIteration() >= highscore) {
				highscore = warnsdorff.getIteration();
				System.out.println(highscore);
			}
			else {
				System.err.println("Not 64");
			}

			scanner.nextLine();
		}

	}

}

