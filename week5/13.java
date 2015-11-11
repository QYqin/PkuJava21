public class Solution {
	public int romanToInt(String s) {
		if (s.length() <= 0)
			return 0;
		int ints = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				char first = s.charAt(0);
				ints = getRomanValue(first);
			} else {
				char tem = s.charAt(i);
				char temp = s.charAt(i - 1);
				int value = getRomanValue(tem);
				int exvalue = getRomanValue(temp);
				if (value > exvalue) {
					ints = ints + value - 2 * exvalue;
				} else {
					ints = ints + value;
				}
			}
		}
		return ints;
	}

	public int getRomanValue(char x) {
		switch (x) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}

	}
}