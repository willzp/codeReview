import java.util.ArrayList;
import java.util.Arrays;

public class BoardRenderer {

	static String neutral = "-", player1 = "X", player2 = "O";

	ArrayList<String> row0 = new ArrayList<String>(/* Arrays.asList(" ", "1", "2", "3", "4", "5", "6", "7", "8") */);
	ArrayList<String> row1 = new ArrayList<String>(/*Arrays.asList("A", "X", "-", "-", "X", "-", "-", "-", "-")*/);
	ArrayList<String> row2 = new ArrayList<String>(/*Arrays.asList("B", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row3 = new ArrayList<String>(/*Arrays.asList("C", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row4 = new ArrayList<String>(/*Arrays.asList("D", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row5 = new ArrayList<String>(/*Arrays.asList("E", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row6 = new ArrayList<String>(/*Arrays.asList("F", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row7 = new ArrayList<String>(/*Arrays.asList("G", "-", "-", "-", "-", "-", "-", "-", "-")*/);
	ArrayList<String> row8 = new ArrayList<String>(/*Arrays.asList("H", "-", "-", "-", "-", "-", "-", "-", "-")*/);

	ArrayList<String>[] allArrayLists = new ArrayList[9];

	public String RenderBoard(String playerMove, String player) {

		String returnString = "";
		String playerMark = "";

		// Tells BoardRenderer which player makes the move
		if (player.equals("Player1")) {
			playerMark = player1;
		} else if (player.equals("Player2")) {
			playerMark = player2;
		} else {
			returnString = "Invalid player";
			return returnString;
		}
		DrawBoard(playerMove, playerMark);

		return returnString;
	}

	// Renders the board each time a player makes a move
	private void DrawBoard(String playerMove, String playerMark) {

		// Breaking down playermove so it can be read (for example; A4 -> A, 4)
		int number = Integer.parseInt(playerMove.substring(1, 2));
		String letter = playerMove.substring(0, 1);

		// Converting letter to a readable number
		int letterIndex = translateLetter(letter);

		/// Finding correct row and column ///
		// Letter (row) first - then number (column)
		System.out.println(allArrayLists[letterIndex].get(number));
		
		allArrayLists[letterIndex].set(number, playerMark);
		
		

		// Draws out board after positions have been updated
		System.out.println(formatRow(row0));
		System.out.println(formatRow(row1));
		System.out.println(formatRow(row2));
		System.out.println(formatRow(row3));
		System.out.println(formatRow(row4));
		System.out.println(formatRow(row5));
		System.out.println(formatRow(row6));
		System.out.println(formatRow(row7));
		System.out.println(formatRow(row8));
	}


	@SuppressWarnings("rawtypes")
	private String formatRow(ArrayList arrayList) {
		String returnString = arrayList.toString().replace(",", " |").replace("]", " |").trim();

		return returnString;
	}

	private int translateLetter(String letter) {
		int convertedNum = 0;

		if (letter.equals("A") || letter.equals("a")) {
			convertedNum = 1;
		} else if (letter.equals("B") || letter.equals("b")) {
			convertedNum = 2;
		} else if (letter.equals("C") || letter.equals("c")) {
			convertedNum = 3;
		} else if (letter.equals("D") || letter.equals("d")) {
			convertedNum = 4;
		} else if (letter.equals("E") || letter.equals("e")) {
			convertedNum = 5;
		} else if (letter.equals("F") || letter.equals("f")) {
			convertedNum = 6;
		} else if (letter.equals("G") || letter.equals("g")) {
			convertedNum = 7;
		} else if (letter.equals("H") || letter.equals("h")) {
			convertedNum = 8;
		}

		return convertedNum;
	}
	

	// Only run once
	public void startGame() {
		row0.add(" ");
		row0.add("1");
		row0.add("2");
		row0.add("3");
		row0.add("4");
		row0.add("5");
		row0.add("6");
		row0.add("7");
		row0.add("8");
		
		row1.add("A");
		addToList(row1);

		row2.add("B");
		addToList(row2);
		
		row3.add("C");
		addToList(row3);
		
		row4.add("D");
		addToList(row4);
		
		row5.add("E");
		addToList(row5);
		
		row6.add("F");
		addToList(row6);
		
		row7.add("G");
		addToList(row7);
		
		row8.add("H");
		addToList(row8);
		

		allArrayLists[0] = row0;
		allArrayLists[1] = row1;
		allArrayLists[2] = row2;
		allArrayLists[3] = row3;
		allArrayLists[4] = row4;
		allArrayLists[5] = row5;
		allArrayLists[6] = row6;
		allArrayLists[7] = row7;
		allArrayLists[8] = row8;
	}
	
	private void addToList(ArrayList<String> arrayList) {
		for (int i = 0; i < 8; i++) {
			arrayList.add("-");
		}
	}
}
