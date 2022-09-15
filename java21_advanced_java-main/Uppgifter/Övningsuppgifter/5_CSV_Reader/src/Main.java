import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src/Materiallista.csv"));

		int iteration = 0;

		while (sc.hasNextLine()) {
			iteration++;

			String[] line = sc.nextLine().split(",", 3);

			if (iteration == 1) {
				line[2] = " " + line[2];
			}
			else if (iteration > 2) {
				int num1 = Integer.parseInt(line[1].substring(0, line[1].indexOf(" ")));
				int num2 = Integer.parseInt(line[1].substring(line[1].indexOf("x") + 1, line[1].lastIndexOf(")")));
				line[2] = " Total Amount: " + Integer.toString(num1 * num2);
			}
			System.out.println(line[0] + " " + line[1] + line[2]);
		}

		sc.close();
	}

}
