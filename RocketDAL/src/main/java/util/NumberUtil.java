package util;

import java.text.NumberFormat;

public class NumberUtil {

	public static boolean isInt(String input) {
        return input.matches("\\d+");
    }

	public static boolean isFloat(String input) {
        return input.matches("\\d+\\.\\d*");
    }

	public static double format(double input) {
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		df.setGroupingUsed(false);
		return Double.valueOf(df.format(input));
	}
}

