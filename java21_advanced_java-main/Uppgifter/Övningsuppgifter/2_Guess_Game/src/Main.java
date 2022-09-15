import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int randomNum;

		Scanner scan = new Scanner(System.in);

		randomNum = (int) Math.floor((Math.random()*899)+100);
		
		String temp = "";
		for (int i = 0; i < 3; i++) {
			temp += String.valueOf( (int)Math.floor(Math.random()*10));
		}
		randomNum = Integer.parseInt(temp);
		
		System.out.println(randomNum);
		
		//System.out.println("Number is: " + randomNum);
		System.out.println("Please type a random number!");

		for (int i = 0; i < 5; i++) {
			int guess = scan.nextInt();

			if (checkNumValid(guess)) {
				if (compareGuess(guess, randomNum).equals("OOO")) {
					System.out.println("You win!"); 
					i = 5;
				}
				else if (!"OOO".equals(compareGuess(guess, randomNum))) {
					System.out.println("Result: [" + compareGuess(guess, randomNum) + "] " + (5- (i+ 1)) + " Attempts left.");
					if (i >= 4) {
						System.out.println("You lose!");
					}
				}
			} else {
				System.out.println("Invalid number, please guess a number between 100 and 999.");
			}
		}
		scan.close();
	}

	public static boolean checkNumValid(int inputNum) {
		if (inputNum <= 99 || inputNum >= 1000) {
			return false;
		} else {
			return true;
		}
	}

	public static String compareGuess(int guess, int randomNum) {
		String result = "";
		String numbers = Integer.toString(guess);
		String targetNum = Integer.toString(randomNum);
		String num1 = numbers.substring(0, 1);
		String num2 = numbers.substring(1, 2);
		String num3 = numbers.substring(2, 3);

		// Checking first number
		if (num1.contains(targetNum.substring(0, 1))) {
			result += "O";
		} else if (targetNum.contains(num1)) {
			result += "?";
		} else {
			result += "X";
		}

		// Checking second number
		if (num2.contains(targetNum.substring(1, 2))) {
			result += "O";
		} else if (targetNum.contains(num2)) {
			result += "?";
		} else {
			result += "X";
		}

		// Checking third number
		if (num3.contains(targetNum.substring(2, 3))) {
			result += "O";
		} else if (targetNum.contains(num3)) {
			result += "?";
		} else {
			result += "X";
		}

		return result;
	}
}
