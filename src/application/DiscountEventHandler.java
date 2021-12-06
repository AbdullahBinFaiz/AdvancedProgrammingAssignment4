package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DiscountEventHandler {
	
	private Scene toMenuScene;
	
	@FXML
	public TextField originalPrice;
	@FXML
	public TextField discountPercentage;
	@FXML
	public Label discountedPrice;
	@FXML
	public Label savePrice;
	
	private Discount discountObj = new Discount();
	
	public void setScene(Scene scene) {
		toMenuScene = scene;
	}
	
	private boolean checkIfNumbersOnly(String number) {
		for (int i = 0; i < number.length(); i++) {
			if ( !((number.charAt(i) >= '0' && number.charAt(i) <= '9') || (number.charAt(i) == '.')) ) {
				return false;
			}
		}
		return true;
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String numberOne = originalPrice.getText();
		String numberTwo = discountPercentage.getText();
		if (checkIfNumbersOnly(numberOne) && checkIfNumbersOnly(numberTwo)) {
			double valueOne = Double.parseDouble(numberOne);
			double valueTwo = Double.parseDouble(numberTwo);
			if (valueTwo > 100 || valueTwo < 0) {
				savePrice.setText("ERROR");
				return;
			}
			double discount = discountObj.calculate(valueOne, valueTwo);
			double save = valueOne - discount;
			String discountString = String.valueOf(discount);
			String saveString = String.valueOf(save);
			discountedPrice.setText(saveString);
			savePrice.setText(discountString);
		}
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		originalPrice.setText("");
		discountPercentage.setText("");
		discountedPrice.setText("");
		savePrice.setText("");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
}
