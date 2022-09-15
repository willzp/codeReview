import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String playerMove = null;
		String playerName = null;
		boolean isGameOver = false;
		boolean isGameTerminated = false;

		Scanner scan = new Scanner(System.in);
		BoardRenderer br = new BoardRenderer();
		GameLogic gl = new GameLogic();

		br.startGame();

		String firstPlayer = gl.decideFirstMove();

		for (int i = 0; i < 200; i++) {
			// Checks if game has been completed
			isGameOver = br.checkBoardStatus();
			String restartDecision = null;
			while (isGameOver) {
				System.out.println("Game over! Do you wish to restart? (Yes/No)");
				restartDecision = scan.nextLine().toUpperCase();
				if (restartDecision.equals("YES")) {
					br.resetGame();
					System.out.println("Game has been reset.");
					isGameOver = false;
				} else if (restartDecision.equals("NO")) {
					System.out.println("Game session has been terminated.");
					isGameOver = false;
					isGameTerminated = true;
					//changed i = 200;
					return;
				} else {
					System.out.println("Invalid answer, try again.");
				}
			}

			// Continues to run game as long as the player hasn't said "No" in restart phase
			if (!isGameTerminated) {
				// Selects which player starts and whose turn it is
				if (firstPlayer.equals("Player1")) {
					if (i % 2 == 0) {
						playerName = "Player1";
					} else {
						playerName = "Player2";
					}
				} else {
					if (i % 2 == 0) {
						playerName = "Player2";
					} else {
						playerName = "Player1";
					}
				}

				// Player has 3 attempts to make a valid move
				for (int j = 0; j < 3; j++) {
					System.out.println(playerName + ", please type a postion.");
					String attemptedMove = scan.nextLine();

					// First check for valid input
					boolean isValidMove = gl.checkValidMove(attemptedMove);
					if (isValidMove) {

						// Then if move is possible
						boolean canMove = br.checkMove(attemptedMove);

						// Checks if attempted move to tile is possible (not taken)
						if (canMove) {
							playerMove = attemptedMove;
							br.RenderBoard(playerMove, playerName);
							//changed j = 3;
							return;
						} else {
							System.out.println("Cannot perform move, that tile has already been taken. Try again!");
						}
					}

				}
			}
		}

		scan.close();
	}

}
