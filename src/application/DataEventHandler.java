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

public class DataEventHandler {

	private Scene toMenuTwoScene;
	ArrayList<ExchangeRate> dataRates;
	
	@FXML
	public TextField valueOne;
	@FXML
	public Label valueTwo;
	@FXML
	public MenuButton dropdownOne;
	@FXML
	public MenuButton dropdownTwo;
	@FXML
	public Label dataToDataOne;
	@FXML
	public Label dataToDataTwo;
	@FXML
	public Label dataRateOne;
	@FXML
	public Label dataRateTwo;
	
	public void setScene(Scene scene) {
		toMenuTwoScene = scene;
		try {
			FileReader fIn = new FileReader("src/application/files/data.txt");
			BufferedReader bIn = new BufferedReader(fIn);
			String line = null;
			dataRates = new ArrayList<ExchangeRate>();
			while ( (line = bIn.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				String dataOne = token.nextToken();
				String dataTwo = token.nextToken();
				String value = token.nextToken();
				double rate = Double.parseDouble(value);
				ExchangeRate newRate = new ExchangeRate(dataOne, dataTwo, rate);
				dataRates.add(newRate);
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
	
	private double getDataRate(String inputData, String conversionData) {
		for (int i = 0; i < dataRates.size(); i++) {
			ExchangeRate rateObj = dataRates.get(i);
			double rate = rateObj.getRate(inputData, conversionData);
			if (rate != -1) {
				return rate;
			}
		}
		return 0;
	}
	
	private void update(String dataOne, String dataTwo) {
		double rateOne = getDataRate(dataOne, dataTwo);
		double rateTwo = getDataRate(dataTwo, dataOne);
		String rateOneString = String.valueOf(rateOne);
		String rateTwoString = String.valueOf(rateTwo);
		dataToDataOne.setText("1 " + dataOne + " -> " + dataTwo);
		dataToDataTwo.setText("1 " + dataTwo + " -> " + dataOne);
		dataRateOne.setText(rateOneString);
		dataRateTwo.setText(rateTwoString);
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String value = valueOne.getText();
		if (checkIfNumbersOnly(value)) {
			String rateOne = dropdownOne.getText();
			String rateTwo = dropdownTwo.getText();
			double number = Double.parseDouble(value);
			double rate = getDataRate(rateOne, rateTwo);
			double answer = number * rate;
			String answerString = String.valueOf(answer);
			valueTwo.setText(answerString);
		}
	}
	
	@FXML
	public void handleDataOne(ActionEvent event) {
		String data = ((MenuItem)event.getSource()).getText();
		dropdownOne.setText(data);
		update(data, dropdownTwo.getText());
	}
	
	@FXML
	public void handleDataTwo(ActionEvent event) {
		String data = ((MenuItem)event.getSource()).getText();
		dropdownTwo.setText(data);
		update(dropdownOne.getText(), data);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		dropdownOne.setText("Byte");
		dropdownTwo.setText("Byte");
		valueOne.setText("");
		valueTwo.setText("");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuTwoScene);
	}
}
