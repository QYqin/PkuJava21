public class Solution {
	public String intToRoman(int num) {
		if (num < 0 || num > 3999)
			return "False";
		StringBuilder roman = new StringBuilder();
		int[] ints = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		for (int i = 0; num > 0; i++) {
			int count = 0;
			count = num / ints[i];
			num = num % ints[i];
			for (; count > 0; count--) {
				roman.append(romans[i]);
			}
		}
		return roman.toString();
	}
}