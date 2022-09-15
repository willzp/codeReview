import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int maxAttempts = 3;
		String answer;
		String returnStatement;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome, please input a password to login.");
		for (int i = 0; i < maxAttempts; i++) {
			answer = scan.nextLine();
			returnStatement = "";
			if (checkPassword(answer)) {
				returnStatement = "Successfully logged in";
				i = maxAttempts;
			}
			else if (!checkPassword(answer) && i < maxAttempts - 1){
				returnStatement = ("Incorrect password! " + ((maxAttempts - (i + 1)) + " Attempts left!"));
			}
			else {
				returnStatement = "You are out of attempts!";
			}
			System.out.println(returnStatement);
		}
		
		scan.close();
	}

	public static boolean checkPassword(String input) {
		boolean isPasswordValid;
		if (input.equals("DangerKate91")) {
			isPasswordValid = true;
		}
		else {
			isPasswordValid = false;
		}
		return isPasswordValid;
	}
}
