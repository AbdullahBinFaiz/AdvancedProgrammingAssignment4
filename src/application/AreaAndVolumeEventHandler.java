package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AreaAndVolumeEventHandler {
	
	private Scene toMenuTwoScene;
	@FXML
	public TextField value;
	@FXML
	public Label area;
	@FXML
	public Label volume;
	
	public void setScene(Scene scene) {
		toMenuTwoScene = scene;
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
		String valueString = value.getText();
		if (checkIfNumbersOnly(valueString)) {
			double number = Double.parseDouble(valueString);
			double numberSquared = Math.pow(number, 2);
			double numberCubed = Math.pow(number, 3);
			String numberSquaredString = String.valueOf(numberSquared);
			String numberCubedString = String.valueOf(numberCubed);
			area.setText(numberSquaredString);
			volume.setText(numberCubedString);
		}
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuTwoScene);
	}
}
