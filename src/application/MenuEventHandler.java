package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuEventHandler {
	
	private Scene calculatorScene;
	private Scene bmiScene;
	private Scene discountScene;
	private Scene currencyScene;
	private Scene ageScene;
	private Scene investmentScene;
	private Scene menuTwoScene;
	
	public void setCalcScene(Scene scene) {
		calculatorScene = scene;
	}
	
	public void setBMIScene(Scene scene) {
		bmiScene = scene;
	}
	
	public void setDiscountScene(Scene scene) {
		discountScene = scene;
	}
	
	public void setCurrencyScene(Scene scene) {
		currencyScene = scene;
	}
	
	public void setAgeScene(Scene scene) {
		ageScene = scene;
	}
	
	public void setInvestmentScene(Scene scene) {
		investmentScene = scene;
	}
	
	public void setMenuTwoScene(Scene scene) {
		menuTwoScene = scene;
	}
	
	@FXML
	public void openCalculator(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(calculatorScene);
	}
	
	@FXML
	public void openBMI(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(bmiScene);
	}
	
	@FXML
	public void openDiscount(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(discountScene);
	}
	
	@FXML
	public void openCurrency(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(currencyScene);
	}
	
	@FXML
	public void openAge(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(ageScene);
	}
	
	@FXML
	public void openInvestment(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(investmentScene);
	}
	
	@FXML
	public void openMenuTwo(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(menuTwoScene);
	}

}
