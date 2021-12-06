package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InvestmentEventHandler {
	
	private Scene toMenuScene;
	
	@FXML
	public TextField investment;
	@FXML
	public TextField interestPercentage;
	@FXML
	public TextField years;
	@FXML
	public TextField months;
	@FXML
	public Label totalInvestment;
	@FXML
	public Label totalInterest;
	@FXML
	public Label investmentOriginal;
	
	public void setScene(Scene scene) {
		toMenuScene = scene;
	}
	
	private double calculateInterest(double investment, double interestPercentage, double years, double months) {
		//Final Value = Investment*(1 + rate/1200)^(years*12 + months)
		double rate = 1 + (interestPercentage / 1200);
		double time = (years * 12) + months;
		double answer = investment*Math.pow(rate, time);
		return answer;
	}
	
	private boolean checkIfNumbersOnly(String number) {
		for (int i = 0; i < number.length(); i++) {
			if ( !(number.charAt(i) >= '0' && number.charAt(i) <= '9') ) {
				return false;
			}
		}
		return true;
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		String investmentString = investment.getText();
		String interestPercentageString = interestPercentage.getText();
		String yearsString = years.getText();
		String monthsString = months.getText();
		if (checkIfNumbersOnly(yearsString) && checkIfNumbersOnly(monthsString)) {
			double investmentVal = Double.parseDouble(investmentString);
			double interestPercentageVal = Double.parseDouble(interestPercentageString);
			double yearsVal = Double.parseDouble(yearsString);
			double monthsVal = Double.parseDouble(monthsString);
			double answer = calculateInterest(investmentVal, interestPercentageVal, yearsVal, monthsVal);
			String answerString = String.valueOf(answer);
			double calcInterest = answer - investmentVal;
			String calcInterestString = String.valueOf(calcInterest);
			totalInvestment.setText(answerString);
			totalInterest.setText(calcInterestString);
			investmentOriginal.setText(investmentString);
		}
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
}
