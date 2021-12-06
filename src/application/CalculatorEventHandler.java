package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CalculatorEventHandler {
	
	private Scene toMenuScene;
	
	@FXML
	public Label mainBoard;
	@FXML
	public Label prevVal;
	@FXML
	public Label currSign;
	@FXML
	public Label answer;
	
	private boolean init = true;
	private Calculator calculatorObj = new Calculator();
	
	public void setScene(Scene scene) {
		toMenuScene = scene;
	}
	
	@FXML
	public void handleNumber(ActionEvent event) {
		if (init) {
			mainBoard.setText("");
			prevVal.setText("");
			currSign.setText("");
			answer.setText("");
			init = false;
		}
		String value = ((Button)event.getSource()).getText();
		String number = mainBoard.getText();
		if (number.length() < 12) {
			mainBoard.setText(mainBoard.getText() + value);
		}
	}
	
	@FXML
	public void handleOperator(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
		String failsafe = mainBoard.getText();
		if (failsafe.length() == 0) {
			if (value.equals("-")) {
				mainBoard.setText("-");
			}
			return;
		}
		if (value.equals("+") || value.equals("-") || value.equals("/") || value.equals("*") || value.equals("%")) {
			if (prevVal.getText().length() == 0) { 
				String number = mainBoard.getText();
				prevVal.setText(number);
				currSign.setText(value);
				mainBoard.setText("");
			} else {
				String numberOne = mainBoard.getText();
				String numberTwo = prevVal.getText();
				double valueOne = Double.parseDouble(numberOne);
				double valueTwo = Double.parseDouble(numberTwo);
				String operator = currSign.getText();
				double ans = calculatorObj.calculate(valueOne, valueTwo, operator);
				prevVal.setText(String.valueOf(ans));
				currSign.setText(value);
				mainBoard.setText("");
			}
		} else if (value.equals("=")) {
			String numberTwo = mainBoard.getText();
			if (numberTwo.charAt(numberTwo.length()-1) == '.') {
				numberTwo += "0";
			}
			String numberOne = prevVal.getText();
			String operator = currSign.getText();
			if (numberOne.length() > 0 && operator.length() > 0) {
				double valueOne = Double.parseDouble(numberOne);
				double valueTwo = Double.parseDouble(numberTwo);
				double ans = calculatorObj.calculate(valueOne, valueTwo, operator);
				String calculation = String.valueOf(ans);
				answer.setText(calculation);
				init = true;
			}
		} else if (value.equals("DEL")) {
			String number = mainBoard.getText();
			if (number != null && number.length() > 0) {
				String updateValue = number.substring(0, (number.length() - 1));
				mainBoard.setText(updateValue);
			}
		} else if (value.equals("AC")) {
			mainBoard.setText("");
			prevVal.setText("");
			currSign.setText("");
			answer.setText("");
		} else if (value.equals(".")) {
			String number = mainBoard.getText();
			if (number.contains("."))
				return;
			mainBoard.setText(number + value);
		}
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
}
