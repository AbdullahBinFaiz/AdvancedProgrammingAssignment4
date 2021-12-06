package application;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AgeEventHandler {
	
	private Scene toMenuScene;
	
	@FXML
	public DatePicker date;
	@FXML
	public Label ageYear;
	@FXML
	public Label ageMonth;
	@FXML
	public Label ageDay;
	@FXML
	public Label nextDay;
	@FXML
	public Label nextDate;
	@FXML
	public Label summaryYears;
	@FXML
	public Label summaryMonths;
	@FXML
	public Label summaryWeeks;
	@FXML
	public Label summaryDays;
	@FXML
	public Label summaryHours;
	@FXML
	public Label summaryMinutes;
	
	public void setScene(Scene scene) {
		toMenuScene = scene;
	}
	
	private String getDay(int day) {
		switch (day) {
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
			case 7:
				return "Sunday";
			default:
				return "NULL";
		}
	}
	
	@FXML
	public void handleCalculate(ActionEvent event) {
		LocalDate enteredDate = date.getValue();
		LocalDate currentDate = LocalDate.now();
		int years = Period.between(enteredDate, currentDate).getYears();
		int months = Period.between(enteredDate, currentDate).getMonths();
		int days = Period.between(enteredDate, currentDate).getDays();
		String yearsString = String.valueOf(years);
		String monthsString = String.valueOf(months);
		String daysString = String.valueOf(days);
		ageYear.setText(yearsString + " years");
		ageMonth.setText(monthsString + " months");
		ageDay.setText(daysString + " days");
		
		LocalDate nextBirthday = enteredDate.withYear(currentDate.getYear());
		if (nextBirthday.isBefore(currentDate)) {
			nextBirthday = nextBirthday.plusYears(1);
		}

		int day = nextBirthday.getDayOfWeek().getValue();
		int monthsBetween = Period.between(currentDate, nextBirthday).getMonths();
		int daysBetween = Period.between(currentDate, nextBirthday).getDays();
		String dayString = getDay(day);
		String monthsBetweenString = String.valueOf(monthsBetween);
		String daysBetweenString = String.valueOf(daysBetween);
		nextDay.setText(dayString);
		nextDate.setText(monthsBetweenString + " months, " + daysBetweenString + " days");
		summaryYears.setText(yearsString);
		int monthsOld = (years*12) + months;
		String summaryMonthsString = String.valueOf(monthsOld);
		summaryMonths.setText(summaryMonthsString);
		long weeksOld = ChronoUnit.WEEKS.between(enteredDate, currentDate);
		String summaryWeeksString = String.valueOf(weeksOld);
		summaryWeeks.setText(summaryWeeksString);
		long daysOld = ChronoUnit.DAYS.between(enteredDate, currentDate);
		String summaryDaysString = String.valueOf(daysOld);
		summaryDays.setText(summaryDaysString);
		long hours = daysOld * 24;
		String summaryHoursString = String.valueOf(hours);
		summaryHours.setText(summaryHoursString);
		long minutes = hours * 60;
		String summaryMinutesString = String.valueOf(minutes);
		summaryMinutes.setText(summaryMinutesString);
	}
	
	@FXML
	public void handleClear(ActionEvent event) {
		ageYear.setText("");
		ageMonth.setText("");
		ageDay.setText("");
		nextDay.setText("");
		nextDate.setText("");
		summaryYears.setText("");
		summaryMonths.setText("");
		summaryWeeks.setText("");
		summaryDays.setText("");
		summaryHours.setText("");
		summaryMinutes.setText("");
	}
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(toMenuScene);
	}
	
}
