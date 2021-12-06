package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// load up all FXML files
			FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/application/MainMenu.fxml"));
			Parent mainMenu = mainMenuLoader.load();
			FXMLLoader calculatorLoader = new FXMLLoader(getClass().getResource("/application/Calculator.fxml"));
			Parent calculator = calculatorLoader.load();
			FXMLLoader bmiLoader = new FXMLLoader(getClass().getResource("/application/BMI.fxml"));
			Parent bmi = bmiLoader.load();
			FXMLLoader discountLoader = new FXMLLoader(getClass().getResource("/application/Discount.fxml"));
			Parent discount = discountLoader.load();
			FXMLLoader currencyLoader = new FXMLLoader(getClass().getResource("/application/Currency.fxml"));
			Parent currency = currencyLoader.load();
			FXMLLoader ageLoader = new FXMLLoader(getClass().getResource("/application/Age.fxml"));
			Parent age = ageLoader.load();
			FXMLLoader investmentLoader = new FXMLLoader(getClass().getResource("/application/Investment.fxml"));
			Parent investment = investmentLoader.load();
			FXMLLoader areaAndVolumeLoader = new FXMLLoader(getClass().getResource("/application/AreaAndVolume.fxml"));
			Parent areaAndVolume = areaAndVolumeLoader.load();
			FXMLLoader dataLoader = new FXMLLoader(getClass().getResource("/application/Data.fxml"));
			Parent data = dataLoader.load();
			FXMLLoader speedLoader = new FXMLLoader(getClass().getResource("/application/Speed.fxml"));
			Parent speed = speedLoader.load();
			FXMLLoader massLoader = new FXMLLoader(getClass().getResource("/application/Mass.fxml"));
			Parent mass = massLoader.load();
			FXMLLoader temperatureLoader = new FXMLLoader(getClass().getResource("/application/Temperature.fxml"));
			Parent temperature= temperatureLoader.load();
			FXMLLoader mainMenuTwoLoader = new FXMLLoader(getClass().getResource("/application/MainMenu2.fxml"));
			Parent mainMenuTwo = mainMenuTwoLoader.load();
			
			// load up scenes from panes
			Scene mainMenuScene = new Scene(mainMenu);
			Scene calculatorScene = new Scene(calculator);
			Scene bmiScene = new Scene(bmi);
			Scene discountScene = new Scene(discount);
			Scene currencyScene = new Scene(currency);
			Scene ageScene = new Scene(age);
			Scene investmentScene = new Scene(investment);
			Scene areaAndVolumeScene = new Scene(areaAndVolume);
			Scene dataScene = new Scene(data);
			Scene speedScene = new Scene(speed);
			Scene massScene = new Scene(mass);
			Scene temperatureScene = new Scene(temperature);
			Scene mainMenuTwoScene = new Scene(mainMenuTwo);
			
			// injecting scenes into controllers
			CalculatorEventHandler calcEventHandler = (CalculatorEventHandler) calculatorLoader.getController();
			calcEventHandler.setScene(mainMenuScene);
			BMIEventHandler bmiEventHandler = (BMIEventHandler) bmiLoader.getController();
			bmiEventHandler.setScene(mainMenuScene);
			DiscountEventHandler discEventHandler = (DiscountEventHandler) discountLoader.getController();
			discEventHandler.setScene(mainMenuScene);
			CurrencyEventHandler currEventHandler = (CurrencyEventHandler) currencyLoader.getController();
			currEventHandler.setScene(mainMenuScene);
			AgeEventHandler ageEventHandler = (AgeEventHandler) ageLoader.getController();
			ageEventHandler.setScene(mainMenuScene);
			InvestmentEventHandler nvstEventHandler = (InvestmentEventHandler) investmentLoader.getController();
			nvstEventHandler.setScene(mainMenuScene);
			AreaAndVolumeEventHandler anvEventHandler = (AreaAndVolumeEventHandler) areaAndVolumeLoader.getController();
			anvEventHandler.setScene(mainMenuTwoScene);
			DataEventHandler dataEventHandler = (DataEventHandler) dataLoader.getController();
			dataEventHandler.setScene(mainMenuTwoScene);
			SpeedEventHandler speedEventHandler = (SpeedEventHandler) speedLoader.getController();
			speedEventHandler.setScene(mainMenuTwoScene);
			MassEventHandler massEventHandler = (MassEventHandler) massLoader.getController();
			massEventHandler.setScene(mainMenuTwoScene);
			TemperatureEventHandler tempEventHandler = (TemperatureEventHandler) temperatureLoader.getController();
			tempEventHandler.setScene(mainMenuTwoScene);
			MenuEventHandler menuEventHandler = (MenuEventHandler) mainMenuLoader.getController(); // MainMenu will allocate multiple scenes for multiple functionalities
			menuEventHandler.setCalcScene(calculatorScene);
			menuEventHandler.setBMIScene(bmiScene);
			menuEventHandler.setDiscountScene(discountScene);
			menuEventHandler.setCurrencyScene(currencyScene);
			menuEventHandler.setAgeScene(ageScene);
			menuEventHandler.setInvestmentScene(investmentScene);
			menuEventHandler.setMenuTwoScene(mainMenuTwoScene);
			MenuTwoEventHandler menuTwoEventHandler = (MenuTwoEventHandler) mainMenuTwoLoader.getController();
			menuTwoEventHandler.setAreaAndVolumeScene(areaAndVolumeScene);
			menuTwoEventHandler.setDataScene(dataScene);
			menuTwoEventHandler.setSpeedScene(speedScene);
			menuTwoEventHandler.setMassScene(massScene);
			menuTwoEventHandler.setTemperatureScene(temperatureScene);
			menuTwoEventHandler.setMenuOneScene(mainMenuScene);
			
			// set up initial scene and show it
			primaryStage.setTitle("APP");
			primaryStage.setScene(mainMenuScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
