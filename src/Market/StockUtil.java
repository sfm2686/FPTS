package CSV;

/*
 * @author Hunter Caskey, Sultan Mira
 * 
 * This class represents a stock in the market, read from the equities.csv file.
 * This represents individual stocks along with price, name, ticker symbol, sector/index.
 */

public class StockUtil extends EquityUtil {
	private String tickerSymbol;
	private double price;

	public StockUtil(String name, String symbol, String price) {
		super.setName(name);
		this.tickerSymbol = symbol;
		this.price = Double.parseDouble(price);
	}

	public double getPrice() {
		return this.price;
	}

	public String getTickerSymbol() {
		return this.tickerSymbol;
	}

	@Override
	public String toString() {
		return this.tickerSymbol + ", " + getName() + ", " + this.price;
	}

	public static void main(String[] args) {
		StockUtil testStock = new StockUtil("Apple", "APPL", "300");

		int testNum = 3;
		int failCount = 0;

		if (testStock.getName() != "Apple") {
			++failCount;
		}
		if (testStock.getTickerSymbol() != "APPL") {
			++failCount;
		}
		if (testStock.getPrice() != 300.00) {
			++failCount;
		}
		System.out.println("Conducting unit tests for StockUtil:\n" + (testNum - failCount) + " out of " + testNum
				+ " tests passed.");

		System.out.println(testStock);
	}
}
