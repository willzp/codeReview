package application;

import java.time.LocalDate;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {
	private LocalDate orderDate;
	private SimpleStringProperty region, rep1, rep2, item;
	private SimpleIntegerProperty units;
	private SimpleFloatProperty unitCost, total;

	public Order(LocalDate orderDate, String region, String rep1, String rep2, String item, int units, float unitCost, float total) {
		//super();
		this.orderDate = orderDate;
		this.region = new SimpleStringProperty(region);
		this.rep1 = new SimpleStringProperty(rep1);
		this.rep2 = new SimpleStringProperty(rep2);
		this.item = new SimpleStringProperty(item);
		this.units = new SimpleIntegerProperty(units);
		this.unitCost = new SimpleFloatProperty(unitCost);
		this.total = new SimpleFloatProperty(total);
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public String getRegion() {
		return region.get();
	}

	public int getAmount() {
		return units.get();
	}

	public float getPrice() {
		return unitCost.get();
	}

	public String getRep1() {
		return rep1.get();
	}

	public String getRep2() {
		return rep2.get();
	}

	public String getItem() {
		return item.get();
	}

	public float getTotal() {
		return total.get();
	}

	public int getUnits() {
		return units.get();
	}

	public float getUnitCost() {
		return unitCost.get();
	}

	
	
}
