public class Solution {
	public static void sortColors(int[] nums) {
	    //以前的方法是暴力破解，相同的代码循环了6次才通过
		int[] counts = { 0,0,0 }; // 记录每个颜色出现的次数
		for (int i = 0; i < nums.length; i++)
		counts[nums[i]]++;
		int index = 0;
		for (int i = 0; i < 3; i++)
		for (int j = 0; j < counts[i]; j++)
		nums[index++] = i;
	}
}