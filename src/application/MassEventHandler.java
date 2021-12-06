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

public class MassEventHandler {
	
	private Scene toMenuTwoScene;
	ArrayList<ExchangeRate> mass;
	
	@FXML
	public TextField valueOne;
	@FXML
	public Label valueTwo;
	@FXML
	public MenuButton dropdownOne;
	@FXML
	public MenuButton dropdownTwo;
	@FXML
	public Label massToMassOne;
	@FXML
	public Label massToMassTwo;
	@FXML
	public Label massOne;
	@FXML
	public Label massTwo;
	
	public void setScene(Scene scene) {
		toMenuTwoScene = scene;
		try {
			FileReader fIn = new FileReader("src/application/files/mass.txt");
			BufferedReader bIn = new BufferedReader(fIn);
			String line = null;
			mass = new ArrayList<ExchangeRate>();
			while ( (line = bIn.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				String speedOne = token.nextToken();
				String speedTwo = token.nextToken();
				String value = token.nextToken();
				double rate = Double.parseDouble(value);
				ExchangeRate newRate = new ExchangeRate(speedOne, speedTwo, rate);
				mass.add(newRate);
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
	
	private double getMassRate(String inputMass, String conversionMass) {
		for (int i = 0; i < mass.size(); i++) {
			ExchangeRate rateObj = mass.get(i);
			double rate = rateObj.getRate(inputMass, conversionMass);
			if (rate != -1) {
				return rate;
			}
		}
		return 0;
	}
	
	private void update(String mOne, String mTwo) {
		double rateOne = getMassRate(mOne, mTwo);
		double rateTwo = getMassRate(mTwo, mOne);
		String rateOneString = String.valueOf(rateOne);
		String rateTwoString = String.valueOf(rateTwo);
		massToMassOne.setText("1 " + mOne + " -> " + mTwo);
		massToMassTwo.setText("1 " + mTwo + " -> " + mOne);
		massOne.setText(rateOneString);
		massTwo.setText(rateTwoString);
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String value = valueOne.getText();
		if (checkIfNumbersOnly(value)) {
			String rateOne = dropdownOne.getText();
			String rateTwo = dropdownTwo.getText();
			double number = Double.parseDouble(value);
			double rate = getMassRate(rateOne, rateTwo);
			double answer = number * rate;
			String answerString = String.valueOf(answer);
			valueTwo.setText(answerString);
		}
	}
	
	@FXML
	public void handleMassOne(ActionEvent event) {
		String mass = ((MenuItem)event.getSource()).getText();
		dropdownOne.setText(mass);
		update(mass, dropdownTwo.getText());
	}
	
	@FXML
	public void handleMassTwo(ActionEvent event) {
		String mass = ((MenuItem)event.getSource()).getText();
		dropdownTwo.setText(mass);
		update(dropdownOne.getText(), mass);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		dropdownOne.setText("gram");
		dropdownTwo.setText("gram");
		valueOne.setText("");
		valueTwo.setText("");
		update("gram", "gram");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuTwoScene);
	}
	
}
