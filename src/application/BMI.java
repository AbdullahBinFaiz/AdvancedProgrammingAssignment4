package application;

public class BMI {

	public double calculate(double weight, double height) {
		if (height == 0) {
			return -1;
		}
		double answer = (weight / height / height) * 10000;
		return answer;
	}
}
