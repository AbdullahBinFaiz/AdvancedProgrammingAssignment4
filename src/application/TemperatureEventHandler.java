package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TemperatureEventHandler {

	private Scene toMenuTwoScene;
	
	@FXML
	public TextField valueOne;
	@FXML
	public Label valueTwo;
	@FXML
	public MenuButton dropdownOne;
	@FXML
	public MenuButton dropdownTwo;
	@FXML
	
	public void setScene(Scene scene) {
		toMenuTwoScene = scene;
	}
	
	private boolean checkIfNumbersOnly(String number) {
		for (int i = 0; i < number.length(); i++) {
			if ( !((number.charAt(i) >= '0' && number.charAt(i) <= '9') || (number.charAt(i) == '.') || number.charAt(i) == '-') ) {
				return false;
			}
		}
		return true;
	}
	
	private double calculateTemperature(String formatOne, String formatTwo, double value) {
		switch (formatOne) {
			case "C":
				switch (formatTwo) {
					case "C":
						return value;
					case "F":
						double conv = 9.0/5.0;
						return ((value * conv) + 32.0);
					case "K":
						return (value + 273.15);
					default:
						return -1;
				}
			case "F":
				switch (formatTwo) {
				case "C":
					double conv1 = 5.0/9.0;
					return ((value-32.0) * conv1);
				case "F":
					return value;
				case "K":
					double conv2 = 5.0/9.0;
					double intermediate = ((value-32.0) * conv2);
					return (intermediate + 273.15);
				default:
					return -1;
				}
			case "K":
				switch (formatTwo) {
				case "C":
					return (value - 273.15);
				case "F":
					double conv = 9.0/5.0;
					double intermediate = (value-273.15) * conv;
					return (intermediate + 32.0);
				case "K":
					return value;
				default:
					return -1;
				}
			default:
				return -1;
		}
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String value = valueOne.getText();
		if (checkIfNumbersOnly(value)) {
			double number = Double.parseDouble(value);
			String formatOne = dropdownOne.getText();
			String formatTwo = dropdownTwo.getText();
			double answer = calculateTemperature(formatOne, formatTwo, number);
			String answerString = String.valueOf(answer);
			valueTwo.setText(answerString);
		}
	}
	
	@FXML
	public void handleTemperatureOne(ActionEvent event) {
		String temp = ((MenuItem)event.getSource()).getText();
		dropdownOne.setText(temp);
	}
	
	@FXML
	public void handleTemperatureTwo(ActionEvent event) {
		String temp = ((MenuItem)event.getSource()).getText();
		dropdownTwo.setText(temp);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		dropdownOne.setText("C");
		dropdownTwo.setText("C");
		valueOne.setText("");
		valueTwo.setText("");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuTwoScene);
	}
}
