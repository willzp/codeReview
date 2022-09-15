package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonParser {

	private static String jsonFilePath = "src/application/sample.json";

	public static ArrayList<Order> TestJsonRead() throws FileNotFoundException, IOException {

		ArrayList<Order> orderList = new ArrayList<Order>();

		String jsonString = "";

		Scanner sc;
		try {
			sc = new Scanner(new File(jsonFilePath));

			// Reads through json and adds it to string
			while (sc.hasNextLine()) {
				jsonString += sc.nextLine();
			}
			// String before processing
			jsonString = jsonString.replace("[", "").replace("{", "").replace("\"", "").replace("}", "").replace("]",
					"");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Loop through string and add variables to object
		String[] split = jsonString.split(", ");

		// Arraylists used for creating order objects later
		ArrayList<String> orderDateList = new ArrayList<String>();
		ArrayList<String> regionList = new ArrayList<String>();
		ArrayList<String> rep1List = new ArrayList<String>();
		ArrayList<String> rep2List = new ArrayList<String>();
		ArrayList<String> itemList = new ArrayList<String>();
		ArrayList<String> unitsList = new ArrayList<String>();
		ArrayList<String> unitCostList = new ArrayList<String>();
		ArrayList<String> totalList = new ArrayList<String>();

		for (int i = 0; i < split.length; i++) {
			String temp = split[i].replace(" ", "");
//			System.out.println(temp);

			if (temp.contains("OrderDate")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				orderDateList.add(temp);
			} else if (temp.contains("Region")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				regionList.add(temp);
			} else if (temp.contains("Rep1")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				rep1List.add(temp);
			} else if (temp.contains("Rep2")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				rep2List.add(temp);
			} else if (temp.contains("Item")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				itemList.add(temp);
			} else if (temp.contains("Units")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				unitsList.add(temp);
			} else if (temp.contains("UnitCost")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				unitCostList.add(temp);
			} else if (temp.contains("Total")) {
				temp = temp.substring(temp.indexOf(":") + 1);
				totalList.add(temp);
			}
		}

		// Loops through lists to assign variables with their values to a new order
		for (int i = 0; i < totalList.size(); i++) {
			// Date
			int month = Integer.parseInt(orderDateList.get(i).substring(0, orderDateList.get(i).indexOf("/")));
			int day = Integer.parseInt(orderDateList.get(i).substring(orderDateList.get(i).indexOf("/") + 1,
					orderDateList.get(i).lastIndexOf("/")));
			int year = Integer.parseInt(orderDateList.get(i).substring(orderDateList.get(i).lastIndexOf("/") + 1));
			LocalDate orderDate = LocalDate.of(year, month, day);
//			System.out.println(orderDate);

			// Strings
			String region = regionList.get(i);
			String rep1 = rep1List.get(i);
			String rep2 = rep2List.get(i);
			String item = itemList.get(i);

			int units = Integer.parseInt(unitsList.get(i));

			float unitCost;
			// Checks for extra point with zeroes
			if (unitCostList.get(i).contains(".00")) {
				unitCost = Float.parseFloat(unitCostList.get(i).substring(0, unitCostList.get(i).lastIndexOf(".")));
			} else {
				unitCost = Float.parseFloat(unitCostList.get(i));
			}
//			System.out.println("Unit cost: " + unitCost);

			String totalString = totalList.get(i);
			float total;
			// Checks if field is empty
			if (totalString.isEmpty()) {
				totalString = Float.toString(units * unitCost);
			}
			if (totalString.contains(".00")) {
				totalString = totalString.substring(totalString.lastIndexOf("."));
			}
			// Checks for commas
			if (totalString.contains(",")) {
				totalString = totalString.replace(",", "");
			}

			totalString = totalString.replaceAll("\"", "");
			// Checks if field is correct math
			if (Float.parseFloat(totalString) == units * unitCost) {
				total = Float.parseFloat(totalString);
			}
			// Replaces field if math is incorrect
			else {
				total = units * unitCost;
			}
			// Rounds to only 2 decimals
			total = (float) Math.round(total * 100.0) / 100;
//			System.out.println(total);

			// Creates new order with values given
			Order newOrder = new Order(orderDate, region, rep1, rep2, item, units, unitCost, total);
			orderList.add(newOrder);
		}

		System.out.println("Done");

		return orderList;
	}

}
