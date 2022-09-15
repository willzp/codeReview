package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

	@FXML
	private TableView<Order> table;
	@FXML
	private TableColumn<Order, LocalDate> orderDateCol;
	@FXML
	private TableColumn<Order, String> regionCol;
	@FXML
	private TableColumn<Order, String> rep1Col;
	@FXML
	private TableColumn<Order, String> rep2Col;
	@FXML
	private TableColumn<Order, String> itemCol;
	@FXML
	private TableColumn<Order, Integer> unitsCol;
	@FXML
	private TableColumn<Order, Float> unitCostCol;
	@FXML
	private TableColumn<Order, Float> totalCol;

	public ObservableList<Order> orderList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			JsonParser.TestJsonRead();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
		regionCol.setCellValueFactory(new PropertyValueFactory<Order, String>("region"));
		rep1Col.setCellValueFactory(new PropertyValueFactory<Order, String>("rep1"));
		rep2Col.setCellValueFactory(new PropertyValueFactory<Order, String>("rep2"));
		itemCol.setCellValueFactory(new PropertyValueFactory<Order, String>("item"));
		unitsCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("units"));
		unitCostCol.setCellValueFactory(new PropertyValueFactory<Order, Float>("unitCost"));
		totalCol.setCellValueFactory(new PropertyValueFactory<Order, Float>("total"));

		table.setItems(orderList);
	}

	// Assigning orders from read csv file
	public void openCsv(ActionEvent event) {
		orderList.addAll(CsvParser.readCsv());
	}

	// Assigning orders from read json file
	public void openJson(ActionEvent event) throws FileNotFoundException, IOException {
		orderList.addAll(JsonParser.TestJsonRead());
	}

}
