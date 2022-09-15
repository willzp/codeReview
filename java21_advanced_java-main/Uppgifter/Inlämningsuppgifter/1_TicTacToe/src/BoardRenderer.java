import java.util.ArrayList;

public class BoardRenderer {

	static String neutral = "-", player1 = "X", player2 = "O";
	boolean boardStatus = false;// Used for seeing if game is over

	ArrayList<String> row0 = new ArrayList<String>();
	ArrayList<String> row1 = new ArrayList<String>();
	ArrayList<String> row2 = new ArrayList<String>();
	ArrayList<String> row3 = new ArrayList<String>();
	ArrayList<String> row4 = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	ArrayList<String>[] allArrayLists = new ArrayList[5];

	public String RenderBoard(String playerMove, String player) {

		// changed String returnString = "";
		String playerMark = "";

		// Tells BoardRenderer which player makes the move
		if (player.equals("Player1")) {
			playerMark = player1;
		} else if (player.equals("Player2")) {
			playerMark = player2;
		} else {
			// changed returnString = "Invalid player";
			return "Invalid player";
		}
		drawBoard(playerMove, playerMark);

	    // return returnString;
		return "";
	}

	// Renders the board each time a player makes a move
	private void drawBoard(String playerMove, String playerMark) {

		// Breaking down playermove so it can be read (for example; A4 -> A, 4)
		int number = Integer.parseInt(playerMove.substring(1, 2));
		String letter = playerMove.substring(0, 1);

		// Converting letter to a readable number
		int letterIndex = translateLetter(letter);

		/// Finding correct row and column ///
		// Letter (row) first - then number (column)
		allArrayLists[letterIndex].set(number, playerMark);

		// System.out.println(allArrayLists[letterIndex].get(number));

		// Draws out board after positions have been updated
		System.out.println(formatRow(row0));
		System.out.println(formatRow(row1));
		System.out.println(formatRow(row2));
		System.out.println(formatRow(row3));
		System.out.println(formatRow(row4));
	}

	public boolean checkMove(String attemptedMove) {
		// Refer to drawBoard for comments
		boolean returnValue = false;
		String positionValue = "";

		int number = Integer.parseInt(attemptedMove.substring(1, 2));
		String letter = attemptedMove.substring(0, 1);

		int letterIndex = translateLetter(letter);

		// System.out.println("Attempted move: " +
		// allArrayLists[letterIndex].get(number));
		positionValue = getPosition(letterIndex, number);

		if (positionValue.equals("-")) {
			returnValue = true;
		} 
        //changed else { returnValue = false; }
		return returnValue;

	}

	// Checks if there are any matches for a player to win
	public boolean checkBoardStatus() {
		String playerMark = "";
		int winningPlayer = 0;

		// Loop that checks board for each player individually
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				playerMark = "X";
			} else if (i == 1) {
				playerMark = "O";
			}
			// Diagonal matches A1 - D4
			if (getPosition(1, 1).equals(playerMark) && getPosition(2, 2).equals(playerMark)
					&& getPosition(3, 3).equals(playerMark) && getPosition(4, 4).equals(playerMark)) {
				boardStatus = true;
			}
			// Diagonal matches D1 - A4
			else if (getPosition(4, 1).equals(playerMark) && getPosition(3, 2).equals(playerMark)
					&& getPosition(2, 3).equals(playerMark) && getPosition(1, 4).equals(playerMark)) {
				boardStatus = true;
			}
			// Horizontal matches
			for (int j = 1; j < 5; j++) {
				if (getPosition(j, 1).equals(playerMark) && getPosition(j, 2).equals(playerMark)
						&& getPosition(j, 3).equals(playerMark) && getPosition(j, 4).equals(playerMark)) {
					boardStatus = true;
				}
			}
			// Vertical matches
			for (int j = 1; j < 5; j++) {
				if (getPosition(1, j).equals(playerMark) && getPosition(2, j).equals(playerMark)
						&& getPosition(3, j).equals(playerMark) && getPosition(4, j).equals(playerMark)) {
					boardStatus = true;
				}
			}

			if (boardStatus) {
				winningPlayer = getWinner(playerMark);
			    i = 2; // Prevents player 2 from always winning     
			}
		}

		// Check if there is a tie/draw
		if(!boardStatus) {
			boardStatus = checkBoardEmpty();
		}
		
		if (winningPlayer == 1) {
			System.out.println("Player1 wins!");
		} else if (winningPlayer == 2) {
			System.out.println("Player2 wins!");
		}

		return boardStatus;
	}
	
    //changed
	private int getWinner(String playerMark) {
		//int returnNum = 0;
		if (playerMark.equals(player1)) {
			//returnNum = 1;
			return 1;
		} else {
			//returnNum = 2;
			return 2;
		}

		//return returnNum;
	}

	private String getPosition(int letterIndex, int numIndex) {
		// changed String position = allArrayLists[letterIndex].get(numIndex);
		return allArrayLists[letterIndex].get(numIndex);
	}

	@SuppressWarnings("rawtypes")
	private String formatRow(ArrayList arrayList) {
		// changed String returnString = arrayList.toString().replace(",", " |").replace("]", " |").trim();
        return arrayList.toString().replace(",", " |").replace("]", " |").trim();
	}
	
    //changed
	private int translateLetter(String letter) {
		//int convertedNum = 0;

		// make letter to uppercase
		// changed String convertedLetter = letter.toUpperCase();

		if (letter.equalsIgnoreCase("A")) {
			//convertedNum = 1;
			return 1;
		} else if (letter.equalsIgnoreCase("B")) {
			//convertedNum = 2;
			return 2;
		} else if (letter.equalsIgnoreCase("C")) {
			//convertedNum = 3;
			return 3;
		} else if (letter.equalsIgnoreCase("D")) {
			//convertedNum = 4;
			return 4;
		}

		//return convertedNum;
		return 0;
	}

	// Only run once
	public void startGame() {
		row0.add(" ");
		row0.add("1");
		row0.add("2");
		row0.add("3");
		row0.add("4");

		row1.add("A");
		addToList(row1);

		row2.add("B");
		addToList(row2);

		row3.add("C");
		addToList(row3);

		row4.add("D");
		addToList(row4);

		allArrayLists[0] = row0;
		allArrayLists[1] = row1;
		allArrayLists[2] = row2;
		allArrayLists[3] = row3;
		allArrayLists[4] = row4;

		System.out.println(formatRow(row0));
		System.out.println(formatRow(row1));
		System.out.println(formatRow(row2));
		System.out.println(formatRow(row3));
		System.out.println(formatRow(row4));

	}

	// Cleans the board
	public void resetGame() {
		boolean isFirst = true;// Used to skip first row
		for (ArrayList<String> arrayList : allArrayLists) {
			if (!isFirst) {
				for (int i = 1; i < 5; i++) {
					arrayList.set(i, neutral);
				}
			} else {
				isFirst = false;
			}

		}
		boardStatus = false;
	}

	private void addToList(ArrayList<String> arrayList) {
		for (int i = 0; i < 4; i++) {
			arrayList.add(neutral);
		}
	}

	// Checks each row and tile that there is not a '-'
	private boolean checkBoardEmpty() {
		boolean isFirst = true;// Used to skip first row
		int emptyTiles = 0;
		boolean boardEmpty = false;
		for (ArrayList<String> arrayList : allArrayLists) {
			if (!isFirst) {
				for (int i = 1; i < 5; i++) {
					String tileValue = arrayList.get(i);
					if (tileValue.equals(neutral)) {
						emptyTiles++;
					}
				}
			} else {
				isFirst = false;
			}
		}
		if (emptyTiles == 0) {
			boardEmpty = true;
			System.out.println("Board is empty!");
		}

		return boardEmpty;
	}

}
