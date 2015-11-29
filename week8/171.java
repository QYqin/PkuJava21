public class Solution {
	public int titleToNumber(String s) {
		int cur_value = 0;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			cur_value = cur_value
					+ (int) ((cur - 'A' + 1) * Math.pow(26, s.length() - i - 1));
		}
		return cur_value;
	}
}