package lottery;

public class Main {

	public static void main(String[] args) {

		String[] �� = { "05", "14", "19", "23", "28", "32" };
		String[] �� = { "04", "09", "13", "18", "22", "27", "31" };
		String[] ˮ = { "01", "06", "10", "15", "24", "29", "33" };
		String[] ľ = { "03", "08", "12", "17", "21", "26", "30", "35" };
		String[] �� = { "02", "07", "11", "16", "20", "25", "34" };

		Lottery lt = new Lottery();

		lt.buildNum(��, ��, ��, ��, ľ);

	}

}
