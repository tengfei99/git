package lottery;

/**
 * 功能：计算双色球前区号码
 * 
 * @author li
 * 
 */
public class Ssq {

	/**
	 * 功能：由传入的五行数组排列出前区数据
	 * 
	 * @param soil
	 * @param gold
	 * @param water
	 * @param wood
	 * @param fire
	 */
	public void buildNum(String[] soil, String[] gold, String[] water,
			String[] wood, String[] fire, String[] ssq) {

		int j1 = 0;
		int j2 = 0;
		int j3 = 0;
		int j4 = 0;
		int j5 = 0;

		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;

		int odd = 0;
		int sum = 0;

		String space = "";

		int total = 0;

		System.out.println("Begin bulid number:");
		System.out.println("=========================");

		for (int i = 0; i < soil.length; i++) {
			for (j1 = 0; j1 < gold.length; j1++) {
				if (Integer.valueOf(gold[j1]).intValue() > Integer.valueOf(
						soil[i]).intValue()) {
					break;
				}
			}

			if (j1 != gold.length) {
				for (j2 = 0; j2 < water.length; j2++) {
					if (Integer.valueOf(water[j2]).intValue() > Integer
							.valueOf(gold[j1]).intValue()) {
						break;
					}
				}

				if (j2 != water.length) {
					for (j3 = 0; j3 < wood.length; j3++) {
						if (Integer.valueOf(wood[j3]).intValue() > Integer
								.valueOf(water[j2]).intValue()) {
							break;
						}
					}

					if (j3 != wood.length) {
						for (j4 = 0; j4 < fire.length; j4++) {
							if (Integer.valueOf(fire[j4]).intValue() > Integer
									.valueOf(wood[j3]).intValue()) {
								break;
							}
						}

						if (j4 != fire.length) {
							for (j5 = 0; j5 < ssq.length; j5++) {
								if (Integer.valueOf(ssq[j5]).intValue() > Integer
										.valueOf(fire[j4]).intValue()) {
									break;
								}
							}

							for (int k1 = j1; k1 < gold.length; k1++) {
								for (int k2 = j2; k2 < water.length; k2++) {
									if (Integer.valueOf(water[k2]).intValue() > Integer
											.valueOf(gold[k1]).intValue()) {

										for (int k3 = j3; k3 < wood.length; k3++) {
											if (Integer.valueOf(wood[k3])
													.intValue() > Integer
													.valueOf(water[k2])
													.intValue()) {

												for (int k4 = j4; k4 < fire.length; k4++) {
													if (Integer.valueOf(
															fire[k4])
															.intValue() > Integer
															.valueOf(wood[k3])
															.intValue()) {

														for (int k5 = j4; k5 < ssq.length; k5++) {
															if (Integer
																	.valueOf(
																			ssq[k5])
																	.intValue() > Integer
																	.valueOf(
																			fire[k4])
																	.intValue()) {

																total = total + 1;

																sum = Integer
																		.valueOf(soil[i])
																		+ Integer
																				.valueOf(gold[k1])
																		+ Integer
																				.valueOf(water[k2])
																		+ Integer
																				.valueOf(wood[k3])
																		+ Integer
																				.valueOf(fire[k4])
																		+ Integer
																				.valueOf(ssq[k5]);

																if (String
																		.valueOf(
																				sum)
																		.length() == 2) {
																	space = "    ";
																} else {
																	space = "   ";
																}

																if ((Integer
																		.valueOf(soil[i]) % 2) != 0) {
																	odd = odd + 1;
																}
																if ((Integer
																		.valueOf(gold[k1]) % 2) != 0) {
																	odd = odd + 1;
																}
																if ((Integer
																		.valueOf(water[k2]) % 2) != 0) {
																	odd = odd + 1;
																}
																if ((Integer
																		.valueOf(wood[k3]) % 2) != 0) {
																	odd = odd + 1;
																}
																if ((Integer
																		.valueOf(fire[k4]) % 2) != 0) {
																	odd = odd + 1;
																}
																if ((Integer
																		.valueOf(ssq[k5]) % 2) != 0) {
																	odd = odd + 1;
																}
																
																

																if (0 < Integer
																		.valueOf(fire[k4])
																		&& Integer
																				.valueOf(fire[k4]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(fire[k4])
																		&& Integer
																				.valueOf(fire[k4]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(fire[k4])
																		&& Integer
																				.valueOf(fire[k4]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(fire[k4])
																		&& Integer
																				.valueOf(fire[k4]) < 34)
																	n4 = n4 + 1;

																if (0 < Integer
																		.valueOf(wood[k3])
																		&& Integer
																				.valueOf(wood[k3]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(wood[k3])
																		&& Integer
																				.valueOf(wood[k3]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(wood[k3])
																		&& Integer
																				.valueOf(wood[k3]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(wood[k3])
																		&& Integer
																				.valueOf(wood[k3]) < 34)
																	n4 = n4 + 1;

																if (0 < Integer
																		.valueOf(water[k2])
																		&& Integer
																				.valueOf(water[k2]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(water[k2])
																		&& Integer
																				.valueOf(water[k2]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(water[k2])
																		&& Integer
																				.valueOf(water[k2]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(water[k2])
																		&& Integer
																				.valueOf(water[k2]) < 34)
																	n4 = n4 + 1;

																if (0 < Integer
																		.valueOf(gold[k1])
																		&& Integer
																				.valueOf(gold[k1]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(gold[k1])
																		&& Integer
																				.valueOf(gold[k1]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(gold[k1])
																		&& Integer
																				.valueOf(gold[k1]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(gold[k1])
																		&& Integer
																				.valueOf(gold[k1]) < 34)
																	n4 = n4 + 1;

																if (0 < Integer
																		.valueOf(soil[i])
																		&& Integer
																				.valueOf(soil[i]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(soil[i])
																		&& Integer
																				.valueOf(soil[i]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(soil[i])
																		&& Integer
																				.valueOf(soil[i]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(soil[i])
																		&& Integer
																				.valueOf(soil[i]) < 34)
																	n4 = n4 + 1;

																if (0 < Integer
																		.valueOf(ssq[k5])
																		&& Integer
																				.valueOf(ssq[k5]) < 10)
																	n1 = n1 + 1;
																if (9 < Integer
																		.valueOf(ssq[k5])
																		&& Integer
																				.valueOf(ssq[k5]) < 20)
																	n2 = n2 + 1;
																if (19 < Integer
																		.valueOf(ssq[k5])
																		&& Integer
																				.valueOf(ssq[k5]) < 30)
																	n3 = n3 + 1;
																if (29 < Integer
																		.valueOf(ssq[k5])
																		&& Integer
																				.valueOf(ssq[k5]) < 34)
																	n4 = n4 + 1;

																System.out
																		.println(soil[i]
																				+ " "
																				+ gold[k1]
																				+ " "
																				+ water[k2]
																				+ " "
																				+ wood[k3]
																				+ " "
																				+ fire[k4]
																				+ " "
																				+ ssq[k5]
																				+ " = "
																				+ sum
																				+ space
																				+ odd
																				+ ":"
																				+ (6 - odd)
																				+ "   "
																				+ n1
																				+ ":"
																				+ n2
																				+ ":"
																				+ n3
																				+ ":"
																				+ n4);
															}

															odd = 0;
															n1 = 0;
															n2 = 0;
															n3 = 0;
															n4 = 0;
														}

													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println("===========================");
		System.out.println("End bulid,number total:" + total);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] 土 = { "05", "14", "19", "23", "28", "32" };
		String[] 金 = { "04", "09", "13", "18", "22", "27", "31" };
		String[] 水 = { "01", "06", "10", "15", "24", "29", "33" };
		String[] 木 = { "03", "08", "12", "17", "21", "26", "30" };
		String[] 火 = { "02", "07", "11", "16", "20", "25" };

		Ssq lt = new Ssq();

		lt.buildNum(土, 水, 火, 土, 土, 土);

	}

}
