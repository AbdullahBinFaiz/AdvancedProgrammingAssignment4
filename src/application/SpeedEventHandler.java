package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SpeedEventHandler {
	
	private Scene toMenuTwoScene;
	ArrayList<ExchangeRate> speedRates;
	
	@FXML
	public TextField valueOne;
	@FXML
	public Label valueTwo;
	@FXML
	public MenuButton dropdownOne;
	@FXML
	public MenuButton dropdownTwo;
	@FXML
	public Label speedToSpeedOne;
	@FXML
	public Label speedToSpeedTwo;
	@FXML
	public Label speedRateOne;
	@FXML
	public Label speedRateTwo;
	
	public void setScene(Scene scene) {
		toMenuTwoScene = scene;
		try {
			FileReader fIn = new FileReader("src/application/files/speed.txt");
			BufferedReader bIn = new BufferedReader(fIn);
			String line = null;
			speedRates = new ArrayList<ExchangeRate>();
			while ( (line = bIn.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				String speedOne = token.nextToken();
				String speedTwo = token.nextToken();
				String value = token.nextToken();
				double rate = Double.parseDouble(value);
				ExchangeRate newRate = new ExchangeRate(speedOne, speedTwo, rate);
				speedRates.add(newRate);
			}
			bIn.close();
			fIn.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkIfNumbersOnly(String number) {
		for (int i = 0; i < number.length(); i++) {
			if ( !((number.charAt(i) >= '0' && number.charAt(i) <= '9') || (number.charAt(i) == '.')) ) {
				return false;
			}
		}
		return true;
	}
	
	private double getSpeedRate(String inputSpeed, String conversionSpeed) {
		for (int i = 0; i < speedRates.size(); i++) {
			ExchangeRate rateObj = speedRates.get(i);
			double rate = rateObj.getRate(inputSpeed, conversionSpeed);
			if (rate != -1) {
				return rate;
			}
		}
		return 0;
	}
	
	private void update(String speedOne, String speedTwo) {
		double rateOne = getSpeedRate(speedOne, speedTwo);
		double rateTwo = getSpeedRate(speedTwo, speedOne);
		String rateOneString = String.valueOf(rateOne);
		String rateTwoString = String.valueOf(rateTwo);
		speedToSpeedOne.setText("1 " + speedOne + " -> " + speedTwo);
		speedToSpeedTwo.setText("1 " + speedTwo + " -> " + speedOne);
		speedRateOne.setText(rateOneString);
		speedRateTwo.setText(rateTwoString);
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String value = valueOne.getText();
		if (checkIfNumbersOnly(value)) {
			String rateOne = dropdownOne.getText();
			String rateTwo = dropdownTwo.getText();
			double number = Double.parseDouble(value);
			double rate = getSpeedRate(rateOne, rateTwo);
			double answer = number * rate;
			String answerString = String.valueOf(answer);
			valueTwo.setText(answerString);
		}
	}
	
	@FXML
	public void handleSpeedOne(ActionEvent event) {
		String speed = ((MenuItem)event.getSource()).getText();
		dropdownOne.setText(speed);
		update(speed, dropdownTwo.getText());
	}
	
	@FXML
	public void handleSpeedTwo(ActionEvent event) {
		String speed = ((MenuItem)event.getSource()).getText();
		dropdownTwo.setText(speed);
		update(dropdownOne.getText(), speed);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		dropdownOne.setText("mps");
		dropdownTwo.setText("mps");
		valueOne.setText("");
		valueTwo.setText("");
		update("mps", "mps");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuTwoScene);
	}
}
