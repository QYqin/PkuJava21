public class Solution {
   public static void sortColors(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
			if (nums[i] == 0 && i != 0) {
				for (int j = i; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = 0;
			} else if (nums[i] == 2) {
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 2;
			}
		}
	}
}