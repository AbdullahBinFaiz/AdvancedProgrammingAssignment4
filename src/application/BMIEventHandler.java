package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BMIEventHandler {
	
	private Scene toMenuScene;
	
	@FXML
	public Label BMIValue;
	
	@FXML
	public Label BMIText;
	
	@FXML
	public TextField weight;
	@FXML
	public TextField height;
	
	private BMI bmiObj = new BMI();
	
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
	public void handleClear() {
		BMIValue.setText("");
		BMIText.setText("");
		weight.setText("");
		height.setText("");
	}
	
	@FXML
	public void handleCalculate() {
		String firstNumber = weight.getText();
		String secondNumber = height.getText();
		if (checkIfNumbersOnly(firstNumber) && checkIfNumbersOnly(secondNumber)) {
			double firstValue = Double.parseDouble(firstNumber);
			double secondValue = Double.parseDouble(secondNumber);
			double answer = bmiObj.calculate(firstValue, secondValue);
			if ((int)answer == -1) {
				weight.setText("");
				height.setText("");
				BMIValue.setText("");
				BMIText.setText("ERROR");
			} else {
				String answerString = String.valueOf(answer);
				BMIValue.setText(answerString);
				if (answer < 18.5) {
					BMIText.setText("Underweight");
				} else if (answer < 24.9) {
					BMIText.setText("Healthy weight");
				} else if (answer < 29.9) {
					BMIText.setText("Overweight");
				} else {
					BMIText.setText("Obese");
				}
			}
		}
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
}
