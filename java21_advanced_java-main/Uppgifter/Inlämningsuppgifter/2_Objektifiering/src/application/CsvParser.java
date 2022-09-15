package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvParser {

	private static String csvFilePath = "src/application/sample.csv";
	
	// Reads and parses Csv file and returns an Arraylist with the order objects
	public static ArrayList<Order> readCsv() {

		ArrayList<Order> orderList = new ArrayList<Order>();

		Scanner sc;
		try {
			sc = new Scanner(new File(csvFilePath));

			int iteration = 0;

			//Default values
			LocalDate orderDate = LocalDate.of(2000, Month.JANUARY, 01);
			String region, rep1, rep2, item = "";
			int units = 0;
			float unitCost, total = 0;

			// Loops through each row of the .csv file
			while (sc.hasNextLine()) {
				iteration++;

				String[] line = sc.nextLine().split(",", 8);

				if (iteration != 1) {

					// Getting date from string
					int month = Integer.parseInt(line[0].substring(0, line[0].indexOf("/")));
					int day = Integer.parseInt(line[0].substring(line[0].indexOf("/") + 1, line[0].lastIndexOf("/")));
					int year = Integer.parseInt(line[0].substring(line[0].lastIndexOf("/") + 1));
					orderDate = LocalDate.of(year, month, day);
//					System.out.println(orderDate);

					// orderDate = LocalDate.parse(line[0]);
					region = line[1];
					rep1 = line[2];
					rep2 = line[3];
					item = line[4];
					units = Integer.parseInt(line[5]);

					// Checks for extra point with zeroes
					if (line[6].contains(".00")) {
						unitCost = Float.parseFloat(line[6].substring(0, line[6].lastIndexOf(".")));
					} else {
						unitCost = Float.parseFloat(line[6]);
					}
//					System.out.println(unitCost);

					// Checks if field is empty
					if (line[7].isEmpty()) {
						line[7] = Float.toString(units * unitCost);
					}
					if (line[7].contains(".00")) {
						line[7] = line[7].substring(line[7].lastIndexOf("."));
					}
					// Checks for commas
					if (line[7].contains(",")) {
						line[7] = line[7].replace(",", "");
					}

					line[7] = line[7].replaceAll("\"", "");
//					System.out.println(line[7]);
					// Checks if field is correct math
					if (Float.parseFloat(line[7]) == units * unitCost) {
						total = Float.parseFloat(line[7]);
					}
					// Replaces field if math is incorrect
					else {
						total = units * unitCost;
					}
					// Rounds to only 2 decimals
					total = (float) Math.round(total * 100.0) / 100;
//					System.out.println(total);

				} else {
					continue;// Skips first row
				}
				// Creating and adding a new order to the list
				Order newOrder = new Order(orderDate, region, rep1, rep2, item, units, unitCost, total);
				orderList.add(newOrder);

			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderList;
	}

}
