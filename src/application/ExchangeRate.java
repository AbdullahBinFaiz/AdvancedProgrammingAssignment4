package application;

public class ExchangeRate {
	private String currencyOne, currencyTwo;
	private double exchangeRate;
	
	ExchangeRate(String currOne, String currTwo, double rate) {
		currencyOne = currOne;
		currencyTwo = currTwo;
		exchangeRate = rate;
	}
	
	public double getRate(String currOne, String currTwo) {
		if (currOne.equals(currencyOne) && currTwo.equals(currencyTwo))
			return exchangeRate;
		return -1;
	}
}
