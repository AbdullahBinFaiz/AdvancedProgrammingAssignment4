package application;

public class Calculator {
	
	public double calculate(double valueOne, double valueTwo, String sign) {
		if (sign.equals("+")) {
			return (valueOne + valueTwo);
		} else if (sign.equals("-")) {
			return (valueOne - valueTwo);
		} else if (sign.equals("/")) {
			if (valueTwo != 0) {
				return (valueOne / valueTwo);
			}
		} else if (sign.equals("*")) {
			return (valueOne * valueTwo);
		} else if (sign.equals("%")) {
			return (valueOne % valueTwo);
		}
		return 0;
	}
}
