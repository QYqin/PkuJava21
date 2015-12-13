public class Solution {
	public void moveZeroes(int[] nums) {
		int neg = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[neg] = nums[i];
				neg++;
			}
		}
		for (; neg < nums.length; neg++) {
			nums[neg] = 0;
		}
	}
}