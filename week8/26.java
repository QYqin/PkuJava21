public class Solution {
	public int removeDuplicates(int[] nums) {
		int n = nums.length;
		if (n == 0)
			return 0;
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (nums[index] != nums[i])
				//nums[index++] = nums[i];
				nums[++index] = nums[i];// nums[++index]相当于nums[index+1],nums[index++]相当于nums[i],i=i+1
		}
		return index + 1;
	}
}