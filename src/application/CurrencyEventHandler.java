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

public class CurrencyEventHandler {
	
	private Scene toMenuScene;
	ArrayList<ExchangeRate> rates; // kept rates in a container, can add more exchange rates if needed (only has PKR, USD, and EUR by default)
	
	@FXML
	public TextField value;
	@FXML
	public Label convertedValue;
	@FXML
	public MenuButton dropdownOne;
	@FXML
	public MenuButton dropdownTwo;
	@FXML
	public Label currencyToCurrencyOne;
	@FXML
	public Label currencyToCurrencyTwo;
	@FXML
	public Label currencyExchangeRateOne;
	@FXML
	public Label currencyExchangeRateTwo;
	
	public void setScene(Scene scene) {
		toMenuScene = scene;
		try {
			FileReader fIn = new FileReader("src/application/files/rates.txt");
			BufferedReader bIn = new BufferedReader(fIn);
			String line = null;
			rates = new ArrayList<ExchangeRate>();
			while ( (line = bIn.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				String currOne = token.nextToken();
				String currTwo = token.nextToken();
				String value = token.nextToken();
				double rate = Double.parseDouble(value);
				ExchangeRate newRate = new ExchangeRate(currOne, currTwo, rate);
				rates.add(newRate);
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
	
	private double getExchangeRate(String inputCurrency, String conversionCurrency) {
		for (int i = 0; i < rates.size(); i++) {
			ExchangeRate rateObj = rates.get(i);
			double rate = rateObj.getRate(inputCurrency, conversionCurrency);
			if (rate != -1) {
				return rate;
			}
		}
		return 0;
	}
	
	private void update(String currOne, String currTwo) {
		double rateOne = getExchangeRate(currOne, currTwo);
		double rateTwo = getExchangeRate(currTwo, currOne);
		String exchangeOneString = String.valueOf(rateOne);
		String exchangeTwoString = String.valueOf(rateTwo);
		currencyToCurrencyOne.setText("1 " + currOne + " -> " + currTwo);
		currencyToCurrencyTwo.setText("1 " + currTwo + " -> " + currOne);
		currencyExchangeRateOne.setText(exchangeOneString);
		currencyExchangeRateTwo.setText(exchangeTwoString);
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String number = value.getText();
		if (checkIfNumbersOnly(number)) {
			String currOne = dropdownOne.getText();
			String currTwo = dropdownTwo.getText();
			double val = Double.parseDouble(number);
			double rate = getExchangeRate(currOne, currTwo);
			double answer = val * rate;
			String answerString = String.valueOf(answer);
			convertedValue.setText(answerString);
		}
	}
	
	@FXML
	public void handleCurrencyOne(ActionEvent event) {
		String currency = ((MenuItem)event.getSource()).getText();
		dropdownOne.setText(currency);
		update(currency, dropdownTwo.getText());
	}
	
	@FXML
	public void handleCurrencyTwo(ActionEvent event) {
		String currency = ((MenuItem)event.getSource()).getText();
		dropdownTwo.setText(currency);
		update(dropdownOne.getText(), currency);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		dropdownOne.setText("PKR");
		dropdownTwo.setText("USD");
		value.setText("");
		convertedValue.setText("");
		update("PKR", "PKR");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
}
