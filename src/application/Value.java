package application;

public class Value {
	private double value;
	
	Value() {
		value = 0;
	}
	
	Value(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}
