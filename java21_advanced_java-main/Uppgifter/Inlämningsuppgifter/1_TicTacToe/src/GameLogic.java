
public class GameLogic {
    //changed
	public String decideFirstMove() {
		//String returnString = "";

		int randNum = (int) Math.round(Math.random());

		if (randNum == 0) {
			//returnString = "Player1";
			return "Player1";
		} else {
			//returnString = "Player2";
			return "Player2";
		}
         
		//return returnString;

	}

	// Checks if move contains valid characters
	public boolean checkValidMove(String moveToCheck) {
		//changed moveToCheck = moveToCheck.toUpperCase();

		
		String letterToCheck = moveToCheck.substring(0, 1);

		boolean isValid = letterToCheck.equalsIgnoreCase("A") || letterToCheck.equalsIgnoreCase("B") || letterToCheck.equalsIgnoreCase("C")
				|| letterToCheck.equalsIgnoreCase("D");

		if (!isValid) {
			System.out.println("Sorry, your move was invalid try again! Example: d3");
		}
		return isValid;
	}

}