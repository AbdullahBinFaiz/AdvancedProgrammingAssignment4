package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuTwoEventHandler {

	private Scene areaAndVolumeScene;
	private Scene dataScene;
	private Scene speedScene;
	private Scene massScene;
	private Scene temperatureScene;
	private Scene menuOneScene;
	
	public void setAreaAndVolumeScene(Scene scene) {
		areaAndVolumeScene = scene;
	}
	
	public void setDataScene(Scene scene) {
		dataScene = scene;
	}
	
	public void setSpeedScene(Scene scene) {
		speedScene = scene;
	}
	
	public void setMassScene(Scene scene) {
		massScene = scene;
	}
	
	public void setTemperatureScene(Scene scene) {
		temperatureScene = scene;
	}
	
	public void setMenuOneScene(Scene scene) {
		menuOneScene = scene;
	}

	@FXML
	public void openAreaAndVolume(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(areaAndVolumeScene);
	}
	
	@FXML
	public void openData(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(dataScene);
	}
	
	@FXML
	public void openSpeed(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(speedScene);
	}
	
	@FXML
	public void openMass(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(massScene);
	}
	
	@FXML
	public void openTemperature(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(temperatureScene);
	}
	
	@FXML
	public void openMenuOne(MouseEvent e) {
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(menuOneScene);
	}
}
