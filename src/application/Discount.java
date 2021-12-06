package application;

public class Discount {

	public double calculate(double actualPrice, double discountPercentage) {
		double answer = actualPrice * (discountPercentage / 100);
		return answer;
	}
	
}
