import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String playerMove = null;
		String playerName = null;

		Scanner scan = new Scanner(System.in);
		BoardRenderer br = new BoardRenderer();
		
		br.startGame();
		
		for (int i = 0; i < 60; i++) {
			if (i % 2 == 0) {
				playerName = "Player1";
			}
			else {
				playerName = "Player2";
			}
			System.out.println(playerName + ", please type a postion.");
			playerMove = scan.nextLine();
			br.RenderBoard(playerMove, playerName);
		}
	}

}
