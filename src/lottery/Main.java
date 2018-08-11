package lottery;

public class Main {

	public static void main(String[] args) {

		String[] 土 = { "05", "14", "19", "23", "28", "32" };
		String[] 金 = { "04", "09", "13", "18", "22", "27", "31" };
		String[] 水 = { "01", "06", "10", "15", "24", "29", "33" };
		String[] 木 = { "03", "08", "12", "17", "21", "26", "30", "35" };
		String[] 火 = { "02", "07", "11", "16", "20", "25", "34" };

		Lottery lt = new Lottery();

		lt.buildNum(土, 土, 金, 土, 木);

	}

}
